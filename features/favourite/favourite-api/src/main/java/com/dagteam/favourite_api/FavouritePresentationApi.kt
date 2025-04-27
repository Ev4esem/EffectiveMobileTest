package com.dagteam.favourite_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FavouritePresentationApi {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    )
}