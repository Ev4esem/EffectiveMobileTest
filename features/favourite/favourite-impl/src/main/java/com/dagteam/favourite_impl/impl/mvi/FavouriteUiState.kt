package com.dagteam.favourite_impl.impl.mvi

import com.dagteam.domain.models.Course

data class FavouriteUiState(
    val courses: List<Course> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)