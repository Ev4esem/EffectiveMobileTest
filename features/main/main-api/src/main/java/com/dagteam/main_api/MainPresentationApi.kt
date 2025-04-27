package com.dagteam.main_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface MainPresentationApi {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    )
}