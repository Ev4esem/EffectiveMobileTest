package com.dagteam.auth_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface AuthPresentationApi {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onBack: () -> Unit
    )
}