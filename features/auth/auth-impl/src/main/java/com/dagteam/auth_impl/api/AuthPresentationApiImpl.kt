package com.dagteam.auth_impl.api

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dagteam.auth_api.AuthPresentationApi
import com.dagteam.auth_api.AuthPresentationLauncher
import com.dagteam.auth_api.WebPresentationLauncher
import com.dagteam.auth_impl.impl.AuthScreen
import com.dagteam.auth_impl.impl.AuthViewModel
import com.dagteam.auth_impl.impl.OpenSocialMedia
import com.dagteam.auth_impl.impl.mvi.AuthNews
import com.dagteam.main_api.MainPresentationLauncher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class AuthPresentationApiImpl: AuthPresentationApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        onBack: () -> Unit
    ) {
        navGraphBuilder.composable<AuthPresentationLauncher> {
            val viewModel: AuthViewModel = viewModel()
            val coroutineScope = rememberCoroutineScope()
            val uiState = viewModel.uiState.collectAsState()
            BackHandler(onBack = onBack)
            DisposableEffect(Unit) {
                val newsJob = viewModel.news
                    .onEach { news ->
                        when (news) {
                            is AuthNews.NavigateToMain -> {
                                navController.navigate(MainPresentationLauncher)
                            }
                            is AuthNews.NavigateToUrl -> {
                                val encodedUrl =
                                    URLEncoder.encode(news.url, StandardCharsets.UTF_8.toString())
                                navController.navigate("$WebPresentationLauncher/$encodedUrl")
                            }
                        }
                    }
                    .flowOn(Dispatchers.IO)
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
        navGraphBuilder.composable("$WebPresentationLauncher/{url}") { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("encodedUrl")
            if (encodedUrl != null) {
                val decodedUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
                OpenSocialMedia(decodedUrl)
            }
        }
    }
}