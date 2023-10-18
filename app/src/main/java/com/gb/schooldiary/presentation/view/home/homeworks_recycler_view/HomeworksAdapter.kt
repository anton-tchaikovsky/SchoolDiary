package com.gb.schooldiary.presentation.view.home.homeworks_recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.schooldiary.databinding.HomeworkCardViewBinding
import com.gb.schooldiary.domain.entity.Homework

class HomeworksAdapter : RecyclerView.Adapter<HomeworkViewHolder>() {

    private var homeworks: List<Homework> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder =
        HomeworkViewHolder(
            HomeworkCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int =
        homeworks.size

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        holder.bind(homeworks[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHomeworks(homeworks: List<Homework>) {
        this.homeworks = homeworks
        notifyDataSetChanged()
    }
}