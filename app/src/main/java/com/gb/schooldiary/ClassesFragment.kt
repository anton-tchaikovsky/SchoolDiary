package com.gb.schooldiary

import com.gb.schooldiary.databinding.FragmentClassesBinding
import com.gb.schooldiary.utils.ViewBindingFragment

class ClassesFragment : ViewBindingFragment<FragmentClassesBinding>(FragmentClassesBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = ClassesFragment()
    }
}