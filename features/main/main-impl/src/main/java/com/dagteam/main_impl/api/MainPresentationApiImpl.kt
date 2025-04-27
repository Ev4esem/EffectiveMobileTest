package com.dagteam.main_impl.api

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dagteam.main_api.MainPresentationApi
import com.dagteam.main_api.MainPresentationLauncher
import com.dagteam.main_impl.impl.MainScreen
import com.dagteam.main_impl.impl.MainViewModel

class MainPresentationApiImpl: MainPresentationApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onBack: () -> Unit
    ) {
        navGraphBuilder.composable<MainPresentationLauncher> {
            val viewModel: MainViewModel = viewModel()
            val uiState = viewModel.uiState.collectAsState()
            BackHandler(onBack = onBack)
            MainScreen(
                uiState = uiState.value,
                onIntent = {
                    viewModel.acceptIntent(it)
                }
            )
        }
    }
}