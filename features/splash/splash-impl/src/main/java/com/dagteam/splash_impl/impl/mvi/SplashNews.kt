package com.dagteam.splash_impl.impl.mvi

sealed interface SplashNews {
    data object NavigateToAuth: SplashNews
}