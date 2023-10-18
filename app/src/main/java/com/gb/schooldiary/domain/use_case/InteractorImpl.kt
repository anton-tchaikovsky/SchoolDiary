package com.gb.schooldiary.domain.use_case

import com.gb.schooldiary.domain.Repository
import com.gb.schooldiary.domain.entity.Class
import com.gb.schooldiary.domain.entity.Homework
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.properties.Delegates

class InteractorImpl(private val repository: Repository) :
    Interactor {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    private var examDate: Date? = null

    private var todayClasses: List<Class> by Delegates.notNull()

    private val timeState: MutableStateFlow<Long> =
        MutableStateFlow(calculationTimeBeforeExam(examDate))

    init {
        scope.launch {
            withContext(Dispatchers.IO) {
                examDate = repository.getExamDate().time
                timeState.value = calculationTimeBeforeExam(examDate)
            }
        }
    }

    override var isActive: Boolean = false
        set(value) {
            if (value)
                changedTimeBeforeExam()
            field = value
        }

    override fun getTimeBeforeExam(): StateFlow<Long> = timeState

    override suspend fun getTodayClasses(): List<Class> =
        withContext(Dispatchers.IO) {
            repository.getTodayClasses().also {
                todayClasses = it
            }
        }

    override fun getCurrentClassPosition(): Int {
        val currentTimes = Calendar.getInstance(Locale.ENGLISH)
        val endTimes = todayClasses.map {
            it.endTime
        }.map {
            Calendar.getInstance(Locale.ENGLISH).apply {
                set(Calendar.HOUR_OF_DAY, it.substring(0, 2).toInt())
                set(Calendar.MINUTE, it.substring(3, 5).toInt())
            }
        }
        var positionCurrentClass = endTimes.size - 1
        for (i in endTimes.indices) {
            if (currentTimes.before(endTimes[i])) {
                positionCurrentClass = i
                break
            }
        }
        return positionCurrentClass
    }

    override suspend fun getHomeworks(): List<Homework> {
        val homeworks = repository.getHomeworks()
        val daysBeforeHomeworks = getDaysBeforeHomeworks(homeworks.map {
            it.date
        })
        for (i in homeworks.indices) {
            if (daysBeforeHomeworks[i] > 0)
                homeworks[i].daysBeforeDeadline = daysBeforeHomeworks[i]
            if (daysBeforeHomeworks[i] < DEADLINE)
                homeworks[i].isUrgent = true
        }
        return homeworks
    }

    override fun getToday(): String =
        SimpleDateFormat("d MMMM", Locale.ENGLISH).format(Calendar.getInstance(Locale.ENGLISH).time)

    private fun getDaysBeforeHomeworks(dates: List<String>): List<Int> {
        val currentDate = Calendar.getInstance(Locale.ENGLISH)
        return dates.map {
            ((Calendar.getInstance(Locale.ENGLISH).apply {
                set(Calendar.YEAR, it.substring(6, 10).toInt())
                set(Calendar.MONTH, it.substring(3, 5).toInt() - 1)
                set(Calendar.DAY_OF_MONTH, it.substring(0, 2).toInt())
            }.time.time - currentDate.time.time) / (24 * 60 * 60 * 1000)).toInt()
        }
    }

    private fun changedTimeBeforeExam() {
        scope.launch {
            while (isActive) {
                timeState.value = calculationTimeBeforeExam(examDate)
                if (examDate != null)
                    delay(DELAY)
            }
        }
    }

    private fun calculationTimeBeforeExam(examDate: Date?): Long =
        if (examDate != null)
            examDate.time - Calendar.getInstance(Locale.ENGLISH).time.time
        else
            0L

    companion object {
        private const val DELAY = 10000L
        private const val DEADLINE = 3
    }
}