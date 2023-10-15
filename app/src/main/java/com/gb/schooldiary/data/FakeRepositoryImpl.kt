package com.gb.schooldiary.data

import com.gb.schooldiary.domain.Repository
import java.util.Calendar
import java.util.Locale

class FakeRepositoryImpl: Repository {
    override suspend fun getExamDate(): Calendar =
        Calendar.getInstance(Locale.ENGLISH).apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.OCTOBER)
            set(Calendar.DAY_OF_MONTH, 21)
            set(Calendar.HOUR_OF_DAY, 20)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
}