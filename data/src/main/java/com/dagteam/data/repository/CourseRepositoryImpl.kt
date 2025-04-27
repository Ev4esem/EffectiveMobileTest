package com.dagteam.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.dagteam.data.network.CourseApi
import com.dagteam.domain.models.Course
import com.dagteam.domain.models.SortedType
import com.dagteam.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CourseRepositoryImpl : CourseRepository, KoinComponent {

    private val courseApi by inject<CourseApi>()
    private val _coursesFlow = MutableStateFlow<List<Course>>(emptyList())

    override fun getCourses(): Flow<List<Course>> = flow {
        if (_coursesFlow.value.isEmpty()) {
            try {
                val response = courseApi.getCourses()
                _coursesFlow.value = response.courses
            } catch (e: Exception) {
                _coursesFlow.value = emptyList()
                throw e
            }
        }
        emitAll(_coursesFlow)
    }

    override suspend fun changeFavouriteStatus(id: Int) {
        _coursesFlow.update { currentCourses ->
            currentCourses.map { course ->
                if (course.id == id) {
                    course.copy(hasLike = !course.hasLike)
                } else {
                    course
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getSortedCourses(sortedType: SortedType): List<Course> {
        return when (sortedType) {
            SortedType.DESCENDING_ORDER -> {
                _coursesFlow.value.sortedByDescending { it.publishDate.toLocalDate() }
            }
            SortedType.ADDING -> {
                _coursesFlow.value.sortedBy { it.publishDate.toLocalDate() }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun String.toLocalDate(): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(this, formatter)
    }
}