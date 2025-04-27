package com.dagteam.effectivemobiletest.di.features

import com.dagteam.main_api.MainPresentationApi
import com.dagteam.main_impl.api.MainPresentationApiImpl
import com.dagteam.main_impl.impl.MainViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    singleOf(::MainPresentationApiImpl) { bind<MainPresentationApi>() }
    viewModelOf(::MainViewModel)
}