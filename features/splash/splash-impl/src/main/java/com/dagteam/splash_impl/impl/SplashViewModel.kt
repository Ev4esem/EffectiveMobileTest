package com.dagteam.splash_impl.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagteam.splash_impl.impl.mvi.SplashIntent
import com.dagteam.splash_impl.impl.mvi.SplashNews
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    private val _news = MutableSharedFlow<SplashNews>()
    val news: SharedFlow<SplashNews> = _news

    fun acceptIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.PressedContinue -> handlePressedContinue()
        }
    }

    private fun handlePressedContinue() {
        viewModelScope.launch {
            _news.emit(SplashNews.NavigateToAuth)
        }
    }

}