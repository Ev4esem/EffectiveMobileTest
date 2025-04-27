package com.dagteam.effectivemobiletest.di

import com.dagteam.data.repository.CourseRepositoryImpl
import com.dagteam.domain.repositories.CourseRepository
import com.dagteam.domain.use_cases.ChangeFavouriteStatusUseCase
import com.dagteam.domain.use_cases.GetCoursesUseCase
import com.dagteam.domain.use_cases.SortedCoursesUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::CourseRepositoryImpl) { bind<CourseRepository>() }
    factoryOf(::GetCoursesUseCase)
    factoryOf(::ChangeFavouriteStatusUseCase)
    factoryOf(::SortedCoursesUseCase)
}