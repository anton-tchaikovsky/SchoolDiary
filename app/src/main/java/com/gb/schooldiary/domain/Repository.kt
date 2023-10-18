package com.gb.schooldiary.domain

import com.gb.schooldiary.domain.entity.Class
import com.gb.schooldiary.domain.entity.Homework
import java.util.Calendar

interface Repository {
    suspend fun getExamDate(): Calendar

    suspend fun getTodayClasses(): List<Class>

    suspend fun getHomeworks(): List<Homework>
}