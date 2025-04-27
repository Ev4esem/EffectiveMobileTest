package com.dagteam.main_impl.impl.mvi

import com.dagteam.domain.models.SortedType

sealed interface MainIntent {
    data class ChangeFavouriteStatus(val id: Int): MainIntent
    data class SelectedSorted(val sortedType: SortedType): MainIntent
}