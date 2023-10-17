package com.gb.schooldiary.domain

import kotlinx.coroutines.flow.StateFlow

interface Interactor {

    var isActive: Boolean

    fun getTimeBeforeExam(): StateFlow<Long>

    suspend fun getTodayClasses(): List<Class>

    fun getCurrentClassPosition(): Int

}