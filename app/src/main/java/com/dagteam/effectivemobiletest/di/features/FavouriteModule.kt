package com.dagteam.effectivemobiletest.di.features

import com.dagteam.favourite_api.FavouritePresentationApi
import com.dagteam.favourite_impl.api.FavouritePresentationApiImpl
import com.dagteam.favourite_impl.impl.FavouriteViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favouriteModule = module {
    singleOf(::FavouritePresentationApiImpl) { bind<FavouritePresentationApi>() }
    viewModelOf(::FavouriteViewModel)
}