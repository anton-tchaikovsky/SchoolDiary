package com.gb.schooldiary

import com.gb.schooldiary.databinding.FragmentMarksBinding
import com.gb.schooldiary.utils.ViewBindingFragment

class MarksFragment : ViewBindingFragment<FragmentMarksBinding>(FragmentMarksBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = MarksFragment()
    }
}