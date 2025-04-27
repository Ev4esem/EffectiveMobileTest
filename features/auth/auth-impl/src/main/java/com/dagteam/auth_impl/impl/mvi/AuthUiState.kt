package com.dagteam.auth_impl.impl.mvi

import com.dagteam.auth_impl.impl.model.User

data class AuthUiState(
    val user: User = User(),
)
