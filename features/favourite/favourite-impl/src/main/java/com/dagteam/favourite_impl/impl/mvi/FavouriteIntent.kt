package com.dagteam.favourite_impl.impl.mvi

sealed interface FavouriteIntent {
    data class ChangeFavouriteStatus(val id: Int): FavouriteIntent
}