package com.dagteam.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.dagteam.data.network.CourseApi
import com.dagteam.domain.models.Course
import com.dagteam.domain.models.SortedType
import com.dagteam.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CourseRepositoryImpl : CourseRepository, KoinComponent {

    private val _courses = mutableListOf<Course>()
    private var lastUpdated: Long = 0
    private val cacheExpirationTime = 30 * 60 * 1000
    private val courseApi by inject<CourseApi>()

    override fun getCourses(): Flow<List<Course>> {
        return flow {
            if (_courses.isEmpty() || System.currentTimeMillis() - lastUpdated > cacheExpirationTime) {
                try {
                    val coursesFromApi = courseApi.getCourses()
                    _courses.clear()
                    _courses.addAll(coursesFromApi.courses)
                    lastUpdated = System.currentTimeMillis()
                    emit(coursesFromApi.courses)
                } catch (e: Exception) {
                    emit(_courses)
                }
            } else {
                emit(_courses)
            }
        }
    }

    override suspend fun changeFavouriteStatus(id: Int) {
        val index = _courses.indexOfFirst { it.id == id }
        if (index != -1) {
            val course = _courses[index]
            val updatedCourse = course.copy(hasLike = !course.hasLike)
            _courses[index] = updatedCourse
        }
    }

    override suspend fun getCourseById(id: Int): Course? {
        return _courses.find { it.id == id }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sortedCourses(sortedType: SortedType): List<Course> {
        val sortedList = when(sortedType) {
            SortedType.DESCENDING_ORDER -> {
                _courses.sortedByDescending { it.publishDate.toLocalDate() }
            }
            SortedType.ADDING -> {
                _courses.sortedBy { it.publishDate.toLocalDate() }
            }
        }
        return sortedList
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun String.toLocalDate(): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy")
        return LocalDate.parse(this, formatter)
    }
}