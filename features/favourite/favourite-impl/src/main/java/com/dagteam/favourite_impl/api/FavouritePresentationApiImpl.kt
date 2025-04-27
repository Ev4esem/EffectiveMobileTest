package com.dagteam.favourite_impl.api

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dagteam.favourite_api.FavouritePresentationApi
import com.dagteam.favourite_api.FavouritePresentationLauncher
import com.dagteam.favourite_impl.impl.FavouriteScreen
import com.dagteam.favourite_impl.impl.FavouriteViewModel
import org.koin.androidx.compose.koinViewModel

class FavouritePresentationApiImpl: FavouritePresentationApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    ) {
        navGraphBuilder.composable<FavouritePresentationLauncher> {
            val viewModel = koinViewModel<FavouriteViewModel>()
            val uiState = viewModel.uiState.collectAsState()
            FavouriteScreen(
                uiState = uiState.value,
                onIntent = {
                    viewModel.acceptIntent(it)
                }
            )
        }
    }
}