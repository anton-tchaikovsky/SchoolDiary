package com.gb.schooldiary.data

import com.gb.schooldiary.domain.Class
import com.gb.schooldiary.domain.Homework
import com.gb.schooldiary.domain.Repository
import java.util.Calendar
import java.util.Locale

class FakeRepositoryImpl : Repository {
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

    override suspend fun getTodayClasses(): List<Class> =
        listOf(
            Class("History", null, "Mrs. Thomas", "08:00", "08:45", isBaseClass = true, true),
            Class("Literature", null, "Mrs. Barros", "09:00", "09:45", isBaseClass = true, true),
            Class("Physical Education", "Intensive preparation for the Junior World Champion Sheep is Los Angeles", "Mrs. Bully", "10:00", "11:35", isBaseClass = false, false),
            Class("Physics", null, "Mrs. Dump", "12:05", "12:50", isBaseClass = true, true),
            Class("Mathematics", null, "Mrs. Knorr", "13:10", "13:55", isBaseClass = true, true),
            Class("Biology", null, "Mrs. Williams", "14:15", "15:00", isBaseClass = true, true)
        )

    override suspend fun getHomeworks(): List<Homework> =
        listOf(
            Homework("Literature", "Read scenes 1.1 - 1.12 of the Master and Margarita", "19.10.2023", listOf("Mike", "Mishel")),
            Homework("Physics", "Learn Newton's laws of motion","20.10.2023", listOf("Mike", "Bob")),
            Homework("Biology", "Learn Karl's classification principles", "25.10.2023", listOf("Mishel", "Bob"))
        )



}