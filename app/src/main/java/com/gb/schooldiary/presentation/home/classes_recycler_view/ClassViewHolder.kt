package com.gb.schooldiary.presentation.home.classes_recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gb.schooldiary.R
import com.gb.schooldiary.databinding.ClassCardViewBinding
import com.gb.schooldiary.domain.Class
import com.gb.schooldiary.presentation.home.ProviderImageId.getClassImageId

class ClassViewHolder(private val binding: ClassCardViewBinding) : ViewHolder(binding.root) {
    fun bind(itemClass: Class) {
        binding.run {
            classTextView.text = itemClass.name
            timeTextView.text = itemView.context.getString(R.string.time_class)
                .format(itemClass.startTime, itemClass.endTime)
            classImageView.setImageResource(getClassImageId(itemView.context, itemClass.name))
            if (!itemClass.isHasVideo)
                videoLayout.visibility = View.INVISIBLE
        }
    }


}