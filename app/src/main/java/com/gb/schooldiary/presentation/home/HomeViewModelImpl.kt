package com.gb.schooldiary.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.schooldiary.domain.Class
import com.gb.schooldiary.domain.Homework
import com.gb.schooldiary.domain.Interactor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeViewModelImpl(private val interactor: Interactor) : HomeViewModel, ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val timeBeforeExamLiveData: MutableLiveData<Triple<Pair<String, String>, Pair<String, String>, Pair<String, String>>> =
        MutableLiveData()

    private val todayClassesLiveData: MutableLiveData<Pair<List<Class>, Int>> =
        MutableLiveData()

    private val homeworksLiveData: MutableLiveData<List<Homework>> =
        MutableLiveData()

    init {
        observeTimeBeforeExam()
        getTodayClasses()
        getHomeworks()
    }

    private fun getHomeworks() {
        scope.launch {
            homeworksLiveData.value = interactor.getHomeworks()
        }
    }

    private fun observeTimeBeforeExam(){
        scope.launch {
            interactor.getTimeBeforeExam().collect {
                timeBeforeExamLiveData.value = mapTimeBeforeExam(it)
            }
        }
        interactor.isActive = true
    }

    private fun getTodayClasses(){
        scope.launch {
            val todayClasses = interactor.getTodayClasses()
            val currentClassPosition = interactor.getCurrentClassPosition()
            todayClassesLiveData.value = Pair(todayClasses, currentClassPosition)
        }
    }

    override fun getTimeBeforeExamLiveData(): LiveData<Triple<Pair<String, String>, Pair<String, String>, Pair<String, String>>> =
        timeBeforeExamLiveData

    override fun getTodayClassesLiveData() =
        todayClassesLiveData

    override fun getHomeworksLiveData(): LiveData<List<Homework>> =
        homeworksLiveData

    private fun mapTimeBeforeExam(timeBeforeExam: Long): Triple<Pair<String, String>, Pair<String, String>, Pair<String, String>> {
        return if (timeBeforeExam <= 0)
            Triple(Pair("0", "0"), Pair("0", "0"), Pair("0", "0"))
        else {
            val days = timeBeforeExam / (24 * 60 * 60 * 1000)
            val daysTen = days / 10
            val daysOne = days - daysTen * 10
            val hours = timeBeforeExam / (60 * 60 * 1000) % 24
            val hoursTen = hours / 10
            val hoursOne = hours - hoursTen * 10
            val minutes = timeBeforeExam / (60 * 1000) % 60
            val minutesTen = minutes / 10
            val minutesOne = minutes - minutesTen * 10
            Triple(
                Pair(daysTen.toString(), daysOne.toString()),
                Pair(hoursTen.toString(), hoursOne.toString()),
                Pair(minutesTen.toString(), minutesOne.toString())
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        interactor.isActive = false
        scope.cancel()
    }
}