package com.dagteam.splash_impl.api

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dagteam.auth_api.AuthPresentationLauncher
import com.dagteam.splash_api.SplashPresentationApi
import com.dagteam.splash_api.SplashPresentationLauncher
import com.dagteam.splash_impl.impl.SplashScreen
import com.dagteam.splash_impl.impl.SplashViewModel
import com.dagteam.splash_impl.impl.mvi.SplashNews
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SplashPresentationApiImpl: SplashPresentationApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    ) {
        navGraphBuilder.composable<SplashPresentationLauncher> {
            val viewModel: SplashViewModel = viewModel()
            val coroutineScope = rememberCoroutineScope()
            DisposableEffect(Unit) {
                val newsJob = viewModel.news
                    .onEach { news ->
                        when (news) {
                            SplashNews.NavigateToAuth -> navController.navigate(AuthPresentationLauncher)
                        }
                    }
                    .launchIn(coroutineScope)

                onDispose {
                    newsJob.cancel()
                    coroutineScope.cancel()
                }
            }

            SplashScreen(
                onIntent = {
                    viewModel.acceptIntent(it)
                }
            )
        }
    }
}