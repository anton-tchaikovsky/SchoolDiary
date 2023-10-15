package com.gb.schooldiary

import com.gb.schooldiary.databinding.FragmentHomeBinding
import com.gb.schooldiary.utils.ViewBindingFragment

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}