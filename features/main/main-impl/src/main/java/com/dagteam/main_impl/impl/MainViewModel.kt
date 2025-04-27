package com.dagteam.main_impl.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagteam.domain.models.SortedType
import com.dagteam.domain.use_cases.ChangeFavouriteStatusUseCase
import com.dagteam.domain.use_cases.GetCoursesUseCase
import com.dagteam.domain.use_cases.SortedCoursesUseCase
import com.dagteam.main_impl.impl.mvi.MainIntent
import com.dagteam.main_impl.impl.mvi.MainUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val changeFavouriteStatusUseCase: ChangeFavouriteStatusUseCase,
    private val sortedCoursesUseCase: SortedCoursesUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        init()
    }

    fun acceptIntent(intent: MainIntent) {
        when(intent) {
            is MainIntent.ChangeFavouriteStatus -> changeFavouriteStatus(intent.id)
            is MainIntent.SelectedSorted -> sortedCourses(intent.sortedType)
        }
    }

    private fun sortedCourses(sortedType: SortedType) {
        viewModelScope.launch {
           val sortedList = sortedCoursesUseCase(sortedType)
            _uiState.update { currentState ->
                currentState.copy(
                    currentSortedType = sortedType,
                    courses = sortedList
                )
            }
        }
    }

    private fun init() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }

            getCoursesUseCase()
                .catch { error ->
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = error.message
                        )
                    }
                }
                .collect { courses ->
                    _uiState.update {
                        it.copy(
                            courses = courses,
                            loading = false,
                            error = null
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