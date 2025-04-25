package com.dagteam.domain.repositories

import com.dagteam.domain.models.Course
import com.dagteam.domain.models.Courses
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getCourses(): Flow<Courses>

    suspend fun changeFavouriteStatus(id: Int)

    suspend fun getCourseById(id: Int): Course?

}