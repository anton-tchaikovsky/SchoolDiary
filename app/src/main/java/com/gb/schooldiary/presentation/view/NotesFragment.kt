package com.gb.schooldiary.presentation.view

import com.gb.schooldiary.databinding.FragmentNotesBinding
import com.gb.schooldiary.utils.ViewBindingFragment

class NotesFragment : ViewBindingFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}