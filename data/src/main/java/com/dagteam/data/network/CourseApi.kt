package com.dagteam.data.network

import com.dagteam.domain.models.Courses
import retrofit2.http.GET

interface CourseApi {

    @GET("download?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download&authuser=0")
    suspend fun getCourses(): Courses

}