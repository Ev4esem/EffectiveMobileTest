package com.dagteam.auth_impl.api

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dagteam.auth_api.AuthPresentationApi
import com.dagteam.auth_api.AuthPresentationLauncher
import com.dagteam.auth_impl.impl.AuthScreen
import com.dagteam.auth_impl.impl.AuthViewModel
import com.dagteam.auth_impl.impl.mvi.AuthNews
import com.dagteam.main_api.MainPresentationLauncher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AuthPresentationApiImpl: AuthPresentationApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,

    ) {
        navGraphBuilder.composable<AuthPresentationLauncher> {
            val viewModel: AuthViewModel = viewModel()
            val coroutineScope = rememberCoroutineScope()
            val uiState = viewModel.uiState.collectAsState()
            val context = LocalContext.current
            DisposableEffect(Unit) {
                val newsJob = viewModel.news
                    .onEach { news ->
                        when (news) {
                            is AuthNews.NavigateToMain -> {
                                navController.navigate(MainPresentationLauncher)
                            }
                            is AuthNews.NavigateToUrl -> {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                                context.startActivity(intent)
                            }
                        }
                    }
                    .launchIn(coroutineScope)

                onDispose {
                    newsJob.cancel()
                    coroutineScope.cancel()
                }
            }

            AuthScreen(
                uiState = uiState.value,
                onIntent = {
                    viewModel.acceptIntent(it)
                }
            )
        }
    }
}