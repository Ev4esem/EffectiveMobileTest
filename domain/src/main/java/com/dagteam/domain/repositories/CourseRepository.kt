package com.dagteam.domain.repositories

import com.dagteam.domain.models.Course
import com.dagteam.domain.models.SortedType
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getCourses(): Flow<List<Course>>

    suspend fun changeFavouriteStatus(id: Int)

    suspend fun getSortedCourses(sortedType: SortedType): List<Course>

}