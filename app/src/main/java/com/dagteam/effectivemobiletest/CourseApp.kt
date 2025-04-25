package com.dagteam.effectivemobiletest

import android.app.Application
import com.dagteam.effectivemobiletest.di.initKoin

class CourseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}