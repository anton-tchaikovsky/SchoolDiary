package com.gb.schooldiary.presentation.view_model

import androidx.lifecycle.LiveData

interface TimeBeforeExamViewModel {
    fun getTimeBeforeExamLiveData(): LiveData<Triple<Pair<String, String>, Pair<String, String>, Pair<String, String>>>
}