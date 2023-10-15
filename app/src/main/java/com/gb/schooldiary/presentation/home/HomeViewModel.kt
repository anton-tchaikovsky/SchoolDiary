package com.gb.schooldiary.presentation.home

import androidx.lifecycle.LiveData

interface HomeViewModel {
    fun getTimeBeforeExamLiveData(): LiveData<Triple<Pair<String, String>, Pair<String, String>, Pair<String, String>>>
}