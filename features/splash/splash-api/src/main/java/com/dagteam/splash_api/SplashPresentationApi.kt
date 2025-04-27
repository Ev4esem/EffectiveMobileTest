package com.dagteam.splash_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface SplashPresentationApi {

    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onBack: () -> Unit
    )
}