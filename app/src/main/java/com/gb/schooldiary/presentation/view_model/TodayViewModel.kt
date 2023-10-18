package com.gb.schooldiary.presentation.view_model

import androidx.lifecycle.LiveData

interface TodayViewModel {
    fun getTodayLiveData(): LiveData<String>
}