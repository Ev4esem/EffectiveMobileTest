package com.dagteam.domain.use_cases

import com.dagteam.domain.models.SortedType
import com.dagteam.domain.repositories.CourseRepository

class SortedCoursesUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(sortedType: SortedType) =
        repository.getSortedCourses(sortedType)
}