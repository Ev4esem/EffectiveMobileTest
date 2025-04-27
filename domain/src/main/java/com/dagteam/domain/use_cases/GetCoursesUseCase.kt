package com.dagteam.domain.use_cases

import com.dagteam.domain.models.Course
import com.dagteam.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCoursesUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getCourses()
    }
}