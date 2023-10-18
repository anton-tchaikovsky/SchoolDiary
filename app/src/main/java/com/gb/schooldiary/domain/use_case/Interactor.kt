package com.gb.schooldiary.domain.use_case

import com.gb.schooldiary.domain.entity.Class
import com.gb.schooldiary.domain.entity.Homework
import kotlinx.coroutines.flow.StateFlow

interface Interactor {

    var isActive: Boolean

    fun getTimeBeforeExam(): StateFlow<Long>

    suspend fun getTodayClasses(): List<Class>

    fun getCurrentClassPosition(): Int

    suspend fun getHomeworks(): List<Homework>

    fun getToday(): String

}