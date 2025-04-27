package com.dagteam.main_impl.impl.mvi

import com.dagteam.domain.models.Course
import com.dagteam.domain.models.SortedType

data class MainUiState(
    val courses: List<Course> = emptyList(),
    val loading: Boolean = false,
    val currentSortedType: SortedType = SortedType.ADDING,
    val error: String? = null,
)
