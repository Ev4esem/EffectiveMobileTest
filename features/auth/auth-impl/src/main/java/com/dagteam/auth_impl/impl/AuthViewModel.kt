package com.dagteam.auth_impl.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagteam.auth_impl.impl.mvi.AuthIntent
import com.dagteam.auth_impl.impl.mvi.AuthNews
import com.dagteam.auth_impl.impl.mvi.AuthUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val _news = MutableSharedFlow<AuthNews>()
    val news: SharedFlow<AuthNews> = _news

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun acceptIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.PressedVK -> handlePressedButton("https://vk.com/")
            is AuthIntent.ChangeEmail -> handleChangeEmail(intent.email)
            is AuthIntent.ChangePassword -> handleChangePassword(intent.password)
            is AuthIntent.PressedOK -> handlePressedButton("https://ok.ru/")
            is AuthIntent.PressedSignIn -> handlePressedSignIn()
        }
    }

    private fun handlePressedSignIn() {
        viewModelScope.launch {
            _news.emit(AuthNews.NavigateToMain)
        }
    }

    private fun handleChangeEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(
                user = currentState.user.copy(
                    email = email
                )
            )
        }
    }

    private fun handleChangePassword(password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                user = currentState.user.copy(
                    password = password
                )
            )
        }
    }

    private fun handlePressedButton(url: String) {
        viewModelScope.launch {
            _news.emit(AuthNews.NavigateToUrl(url))
        }
    }

}