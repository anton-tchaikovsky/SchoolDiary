package com.gb.schooldiary.presentation.classes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.schooldiary.R
import com.gb.schooldiary.data.FakeRepositoryImpl
import com.gb.schooldiary.databinding.FragmentClassesBinding
import com.gb.schooldiary.domain.Class
import com.gb.schooldiary.domain.InteractorImpl
import com.gb.schooldiary.presentation.classes.classes_recycler_view.ClassesAdapter
import com.gb.schooldiary.presentation.view_model.ViewModelFactory
import com.gb.schooldiary.presentation.view_model.HomeViewModelImpl
import com.gb.schooldiary.presentation.view_model.classes.ClassesViewModel
import com.gb.schooldiary.utils.ViewBindingFragment

class ClassesFragment :
    ViewBindingFragment<FragmentClassesBinding>(FragmentClassesBinding::inflate) {

    private val viewModel: ClassesViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelFactory(InteractorImpl(FakeRepositoryImpl()))
        )[HomeViewModelImpl::class.java]
    }

    private val classesAdapter: ClassesAdapter = ClassesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.run {
            getTodayLiveData().observe(viewLifecycleOwner) {
                setToday(it)
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
        binding.classesRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = classesAdapter
        }
    }

    private fun setToday(today: String){
        binding.todayTextView.text = getString(R.string.today).format(today)
    }

    private fun setTodayClasses(todayClasses: List<Class>, currentClassPosition: Int) {
        classesAdapter.setClasses(todayClasses, currentClassPosition)
        binding.classesRecyclerView.scrollToPosition(currentClassPosition)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClassesFragment()
    }
}