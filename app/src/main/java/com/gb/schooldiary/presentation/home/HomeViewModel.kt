package com.gb.schooldiary.presentation.home

import androidx.lifecycle.LiveData
import com.gb.schooldiary.domain.Class

interface HomeViewModel {
    fun getTimeBeforeExamLiveData(): LiveData<Triple<Pair<String, String>, Pair<String, String>, Pair<String, String>>>

    fun getTodayClassesLiveData(): LiveData<Pair<List<Class>, Int>>
}