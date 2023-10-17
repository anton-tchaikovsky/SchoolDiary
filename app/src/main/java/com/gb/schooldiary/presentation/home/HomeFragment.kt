package com.gb.schooldiary.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.schooldiary.R
import com.gb.schooldiary.data.FakeRepositoryImpl
import com.gb.schooldiary.databinding.FragmentHomeBinding
import com.gb.schooldiary.domain.Class
import com.gb.schooldiary.domain.InteractorImpl
import com.gb.schooldiary.presentation.home.classes_recycler_view.ClassAdapter
import com.gb.schooldiary.utils.ViewBindingFragment

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            HomeViewModelFactory(InteractorImpl(FakeRepositoryImpl()))
        )[HomeViewModelImpl::class.java]
    }

    private val classesAdapter: ClassAdapter = ClassAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.run {
            getTimeBeforeExamLiveData().observe(viewLifecycleOwner) {
                setTimeBeforeExam(it)
            }
            getTodayClassesLiveData().observe(viewLifecycleOwner) {
                setTodayClasses(it.first, it.second)
            }
        }
    }

    private fun initView() {
        initClassesRecyclerView()
    }

    private fun initClassesRecyclerView() {
        binding.classesRecycleView.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
                dividerItemDecoration.setDrawable(it)
            }
            addItemDecoration(dividerItemDecoration)
            adapter = classesAdapter
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

    private fun setTodayClasses(todayClasses: List<Class>, currentClassPosition: Int) {
        classesAdapter.setClasses(todayClasses)
        binding.classesRecycleView.scrollToPosition(currentClassPosition)
        binding.numberClassesTextView.text = getString(R.string.number_classes_today).format(todayClasses.size)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}