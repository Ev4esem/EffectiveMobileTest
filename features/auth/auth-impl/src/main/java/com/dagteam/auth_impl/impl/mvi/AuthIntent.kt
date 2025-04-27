package com.dagteam.auth_impl.impl.mvi

sealed interface AuthIntent {
    data object PressedVK: AuthIntent
    data object PressedOK: AuthIntent
    data object PressedSignIn: AuthIntent
    data class ChangeEmail(val email: String): AuthIntent
    data class ChangePassword(val password: String): AuthIntent
}