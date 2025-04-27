package com.dagteam.effectivemobiletest.di

import com.dagteam.effectivemobiletest.di.features.authModule
import com.dagteam.effectivemobiletest.di.features.favouriteModule
import com.dagteam.effectivemobiletest.di.features.mainModule
import com.dagteam.effectivemobiletest.di.features.splashModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            dataModule,
            domainModule,
            splashModule,
            mainModule,
            authModule,
            favouriteModule,
        )
    }