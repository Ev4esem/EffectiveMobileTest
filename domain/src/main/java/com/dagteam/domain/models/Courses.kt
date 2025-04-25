package com.dagteam.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Courses(
    val courses: List<Course>
)