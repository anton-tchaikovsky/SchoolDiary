package com.gb.schooldiary.presentation.view.classes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.schooldiary.R
import com.gb.schooldiary.databinding.FragmentClassesBinding
import com.gb.schooldiary.domain.entity.Class
import com.gb.schooldiary.presentation.view.classes.classes_recycler_view.ClassesAdapter
import com.gb.schooldiary.presentation.view_model.ViewModelImpl
import com.gb.schooldiary.presentation.view_model.classes.ClassesViewModel
import com.gb.schooldiary.utils.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassesFragment :
    ViewBindingFragment<FragmentClassesBinding>(FragmentClassesBinding::inflate) {

    private val viewModel: ClassesViewModel by viewModel<ViewModelImpl>()

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