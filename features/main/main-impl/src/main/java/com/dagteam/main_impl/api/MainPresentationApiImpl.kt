package com.dagteam.main_impl.api

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dagteam.main_api.MainPresentationApi
import com.dagteam.main_api.MainPresentationLauncher
import com.dagteam.main_impl.impl.MainScreen
import com.dagteam.main_impl.impl.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainPresentationApiImpl: MainPresentationApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    ) {
        navGraphBuilder.composable<MainPresentationLauncher> {
            val viewModel = koinViewModel<MainViewModel>()
            val uiState = viewModel.uiState.collectAsState()
            MainScreen(
                uiState = uiState.value,
                onIntent = {
                    viewModel.acceptIntent(it)
                }
            )
        }
    }
}