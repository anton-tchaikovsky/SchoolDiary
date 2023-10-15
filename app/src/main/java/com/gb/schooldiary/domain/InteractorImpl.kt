package com.gb.schooldiary.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import java.util.Locale

class InteractorImpl(repository: Repository) :
    Interactor {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    private var examDate: Date? = null

    private val timeState: MutableStateFlow<Long> =
        MutableStateFlow(calculationTimeBeforeExam(examDate))

   init {
       scope.launch {
          withContext(Dispatchers.IO){
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

    private fun changedTimeBeforeExam() {
        scope.launch {
            while (isActive) {
                timeState.value = calculationTimeBeforeExam(examDate)
                if (examDate!=null)
                    delay(DELAY)
            }
        }
    }

    private fun calculationTimeBeforeExam(examDate: Date?): Long =
        if (examDate!=null)
            examDate.time - Calendar.getInstance(Locale.ENGLISH).time.time
        else
            0L

    companion object{
        private const val DELAY = 10000L
    }
}