package com.gb.schooldiary.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gb.schooldiary.domain.Interactor

class HomeViewModelFactory (private val interactor: Interactor): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModelImpl(interactor) as T
    }
}