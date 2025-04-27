package com.dagteam.splash_impl.impl.mvi

sealed interface SplashIntent {
    data object PressedContinue: SplashIntent
}