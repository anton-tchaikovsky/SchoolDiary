package com.gb.schooldiary.presentation.home.classes_recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gb.schooldiary.R
import com.gb.schooldiary.databinding.ClassCardViewBinding
import com.gb.schooldiary.domain.Class

class ClassViewHolder(private val binding: ClassCardViewBinding) : ViewHolder(binding.root) {
    fun bind(itemClass: Class) {
        binding.run {
            classTextView.text = itemClass.name
            timeTextView.text = itemView.context.getString(R.string.time_class)
                .format(itemClass.startTime, itemClass.endTime)
            classImageView.setImageResource(getImageId(itemClass.name))
            if (!itemClass.isHasVideo)
                videoLayout.visibility = View.INVISIBLE
        }
    }

    private fun getImageId(nameClass: String): Int=
        when(nameClass){
            itemView.context.getString(R.string.literature) -> R.drawable.literature
            itemView.context.getString(R.string.history) -> R.drawable.history
            itemView.context.getString(R.string.physics) -> R.drawable.physics
            itemView.context.getString(R.string.physical_education) -> R.drawable.physical_education
            itemView.context.getString(R.string.biology) -> R.drawable.biology
            itemView.context.getString(R.string.mathematics) -> R.drawable.mathematics
            else -> R.drawable.education
        }
}