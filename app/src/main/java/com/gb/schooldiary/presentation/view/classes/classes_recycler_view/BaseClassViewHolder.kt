package com.gb.schooldiary.presentation.view.classes.classes_recycler_view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.gb.schooldiary.domain.entity.Class

abstract class BaseClassViewHolder(binding: ViewBinding): ViewHolder(binding.root) {
    abstract fun bind(itemClass: Class, isCurrentClass: Boolean)
}