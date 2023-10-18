package com.gb.schooldiary.presentation.view

import com.gb.schooldiary.databinding.FragmentMarksBinding
import com.gb.schooldiary.utils.ViewBindingFragment

class MarksFragment : ViewBindingFragment<FragmentMarksBinding>(FragmentMarksBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = MarksFragment()
    }
}