package com.gb.schooldiary.koin

import com.gb.schooldiary.data.FakeRepositoryImpl
import com.gb.schooldiary.domain.use_case.Interactor
import com.gb.schooldiary.domain.use_case.InteractorImpl
import com.gb.schooldiary.domain.Repository
import com.gb.schooldiary.presentation.view_model.ViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    single<Repository> {
        FakeRepositoryImpl()
    }

    single<Interactor> { InteractorImpl(
        repository = get()
    ) }

}

val activityModule = module {

    viewModel{
        ViewModelImpl(
            interactor = get()
        )
    }

}