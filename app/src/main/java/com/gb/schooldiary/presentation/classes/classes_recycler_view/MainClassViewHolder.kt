package com.gb.schooldiary.presentation.classes.classes_recycler_view

import android.view.View
import com.gb.schooldiary.R
import com.gb.schooldiary.databinding.MainClassLayoutViewBinding
import com.gb.schooldiary.domain.Class
import com.gb.schooldiary.presentation.home.ProviderImageId

class MainClassViewHolder(private val binding: MainClassLayoutViewBinding) :
    BaseClassViewHolder(binding) {
    override fun bind(itemClass: Class, isCurrentClass: Boolean) {
        binding.run {
            classTextView.text = itemClass.name
            teacherTextView.text = itemView.context.getString(R.string.teacher).format(itemClass.teacher)
            classImageView.setImageResource(
                ProviderImageId.getClassImageId(
                    itemView.context,
                    itemClass.name
                )
            )
            timeTextView.text = itemView.context.getString(R.string.time_class)
                .format(itemClass.startTime, itemClass.endTime)
            if (!itemClass.isHasVideo)
                videoLayout.visibility = View.INVISIBLE
            circleLargeImageView.visibility = if (isCurrentClass)
                View.VISIBLE
            else
                View.INVISIBLE
            if(layoutPosition==0){
                lineImageView.visibility = View.INVISIBLE
            }
        }
    }
}