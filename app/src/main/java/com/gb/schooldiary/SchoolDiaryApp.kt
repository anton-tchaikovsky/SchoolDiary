package com.gb.schooldiary

import android.app.Application
import com.gb.schooldiary.koin.activityModule
import com.gb.schooldiary.koin.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class SchoolDiaryApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SchoolDiaryApp)
            modules(listOf(applicationModule, activityModule))
        }
    }
}