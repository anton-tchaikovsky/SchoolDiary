package com.gb.schooldiary.presentation.view_model

import androidx.lifecycle.LiveData
import com.gb.schooldiary.domain.entity.Class

interface TodayClassesViewModel {
    fun getTodayClassesLiveData(): LiveData<Pair<List<Class>, Int>>
}