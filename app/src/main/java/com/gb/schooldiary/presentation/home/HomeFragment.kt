package com.gb.schooldiary.presentation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.gb.schooldiary.data.FakeRepositoryImpl
import com.gb.schooldiary.databinding.FragmentHomeBinding
import com.gb.schooldiary.domain.InteractorImpl
import com.gb.schooldiary.utils.ViewBindingFragment

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            HomeViewModelFactory(InteractorImpl(FakeRepositoryImpl()))
        )[HomeViewModelImpl::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTimeBeforeExamLiveData().observe(viewLifecycleOwner) {
            setTimeBeforeExam(it)
        }
    }

    private fun setTimeBeforeExam(timeBeforeExam: Triple<Pair<String, String>, Pair<String, String>, Pair<String, String>>) {
        with(binding.examCardView) {
            timeBeforeExam.let {
                dayTenTextView.text = it.first.first
                dayOneTextView.text = it.first.second
                hoursTenTextView.text = it.second.first
                hoursOneTextView.text = it.second.second
                minutesTenTextView.text = it.third.first
                minutesOneTextView.text = it.third.second
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}