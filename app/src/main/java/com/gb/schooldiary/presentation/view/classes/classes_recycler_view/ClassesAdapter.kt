package com.gb.schooldiary.presentation.view.classes.classes_recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.schooldiary.databinding.AdditionalClassLayoutViewBinding
import com.gb.schooldiary.databinding.MainClassLayoutViewBinding
import com.gb.schooldiary.domain.entity.Class

class ClassesAdapter : RecyclerView.Adapter<BaseClassViewHolder>() {

    private var classes: List<Class> = listOf()

    private var currentClassPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseClassViewHolder =
        when (viewType) {
            BASE_TYPE -> MainClassViewHolder(
                MainClassLayoutViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ADDITIONAL_TYPE -> AdditionalClassViewHolder(
                AdditionalClassLayoutViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> {
                MainClassViewHolder(
                    MainClassLayoutViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }


    override fun getItemCount(): Int =
        classes.size

    override fun onBindViewHolder(holder: BaseClassViewHolder, position: Int) {
        holder.bind(classes[position], currentClassPosition==position)
    }

    override fun getItemViewType(position: Int): Int =
        if (classes[position].isBaseClass)
            BASE_TYPE
        else
            ADDITIONAL_TYPE

    @SuppressLint("NotifyDataSetChanged")
    fun setClasses(classes: List<Class>, currentClassPosition: Int) {
        this.classes = classes
        this.currentClassPosition = currentClassPosition
        notifyDataSetChanged()
    }

    companion object {
        private const val BASE_TYPE = 1
        private const val ADDITIONAL_TYPE = 2
    }
}