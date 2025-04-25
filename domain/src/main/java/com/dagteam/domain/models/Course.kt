package com.dagteam.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val hasLike: Boolean,
    val id: Int,
    val price: String,
    val publishDate: String,
    val rate: String,
    val startDate: String,
    val text: String,
    val title: String
)