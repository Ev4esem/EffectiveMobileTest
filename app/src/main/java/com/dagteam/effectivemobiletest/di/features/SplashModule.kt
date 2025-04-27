package com.dagteam.effectivemobiletest.di.features

import com.dagteam.splash_api.SplashPresentationApi
import com.dagteam.splash_impl.api.SplashPresentationApiImpl
import com.dagteam.splash_impl.impl.SplashViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashModule = module {
    singleOf(::SplashPresentationApiImpl) { bind<SplashPresentationApi>() }
    viewModelOf(::SplashViewModel)
}