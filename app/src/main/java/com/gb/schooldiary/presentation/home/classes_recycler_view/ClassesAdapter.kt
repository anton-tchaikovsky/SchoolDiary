package com.gb.schooldiary.presentation.home.classes_recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.schooldiary.databinding.ClassCardViewBinding
import com.gb.schooldiary.domain.Class

class ClassesAdapter : RecyclerView.Adapter<ClassViewHolder>() {

    private var classes: List<Class> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder =
        ClassViewHolder(
            ClassCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int =
        classes.size

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.bind(classes[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setClasses(classes: List<Class>) {
        this.classes = classes
        notifyDataSetChanged()
    }
}