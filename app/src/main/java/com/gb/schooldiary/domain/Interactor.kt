package com.gb.schooldiary.domain

import kotlinx.coroutines.flow.StateFlow

interface Interactor {
    fun getTimeBeforeExam(): StateFlow<Long>
    var isActive: Boolean
}