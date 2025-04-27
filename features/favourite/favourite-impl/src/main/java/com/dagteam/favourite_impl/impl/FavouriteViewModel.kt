package com.dagteam.favourite_impl.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagteam.domain.use_cases.ChangeFavouriteStatusUseCase
import com.dagteam.domain.use_cases.GetCoursesUseCase
import com.dagteam.favourite_impl.impl.mvi.FavouriteIntent
import com.dagteam.favourite_impl.impl.mvi.FavouriteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val changeFavouriteStatusUseCase: ChangeFavouriteStatusUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(FavouriteUiState())
    val uiState: StateFlow<FavouriteUiState> = _uiState.asStateFlow()

    fun acceptIntent(intent: FavouriteIntent) {
        when(intent) {
            is FavouriteIntent.ChangeFavouriteStatus -> changeFavouriteStatus(intent.id)
        }
    }

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch {
            getCoursesUseCase()
                .onEach {
                    _uiState.update { currentState ->
                        currentState.copy(
                            loading = true,
                        )
                    }
                }
                .catch {
                    _uiState.update { currentState ->
                        currentState.copy(
                            loading = false,
                            error = it.message
                        )
                    }
                }
                .collect { courses ->
                    val filterList = courses.filter {
                        it.hasLike
                    }
                    _uiState.update { currentState ->
                        currentState.copy(
                            courses = filterList,
                            loading = false
                        )
                    }
                }
        }
    }

    private fun changeFavouriteStatus(id: Int) {
        viewModelScope.launch {
            changeFavouriteStatusUseCase(id)
        }
    }
}