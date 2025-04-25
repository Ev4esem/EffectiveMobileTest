package com.dagteam.domain.use_cases

import com.dagteam.domain.repositories.CourseRepository

class GetCourseByIdUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(id: Int) = repository.getCourseById(id)
}