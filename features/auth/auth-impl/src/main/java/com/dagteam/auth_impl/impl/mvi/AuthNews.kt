package com.dagteam.auth_impl.impl.mvi

sealed interface AuthNews {
    data object NavigateToMain: AuthNews
    data class NavigateToUrl(val url: String): AuthNews
}