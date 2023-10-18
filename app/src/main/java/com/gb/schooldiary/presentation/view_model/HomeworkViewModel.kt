package com.gb.schooldiary.presentation.view_model

import androidx.lifecycle.LiveData
import com.gb.schooldiary.domain.Homework

interface HomeworkViewModel {
    fun getHomeworksLiveData(): LiveData<List<Homework>>
}