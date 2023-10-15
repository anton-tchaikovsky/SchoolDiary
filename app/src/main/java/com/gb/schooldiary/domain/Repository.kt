package com.gb.schooldiary.domain

import java.util.Calendar

interface Repository {
    suspend fun getExamDate(): Calendar
}