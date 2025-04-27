package com.dagteam.effectivemobiletest.di.features

import com.dagteam.auth_api.AuthPresentationApi
import com.dagteam.auth_impl.api.AuthPresentationApiImpl
import com.dagteam.auth_impl.impl.AuthViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::AuthPresentationApiImpl) { bind<AuthPresentationApi>() }
    viewModelOf(::AuthViewModel)
}