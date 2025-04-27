package com.dagteam.effectivemobiletest.di

import com.dagteam.data.network.CourseApi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    singleOf(::createRetrofit)
    singleOf(::createNoteApi)
}

fun createNoteApi(): CourseApi = createRetrofit().create(CourseApi::class.java)

fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}