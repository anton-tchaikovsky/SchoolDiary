package com.gb.schooldiary.presentation.view.home.homeworks_recycler_view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gb.schooldiary.R
import com.gb.schooldiary.databinding.HomeworkCardViewBinding
import com.gb.schooldiary.domain.entity.Homework
import com.gb.schooldiary.presentation.view.home.ProviderImageId
import com.gb.schooldiary.presentation.view.home.ProviderImageId.getAvatarImageId

class HomeworkViewHolder(private val binding: HomeworkCardViewBinding) : ViewHolder(binding.root) {
    fun bind(homework: Homework) {
        binding.run {
            classTextView.text = homework.name
            classImageView.setImageResource(
                ProviderImageId.getClassImageId(
                    itemView.context,
                    homework.name
                )
            )
            dateTextView.apply {
                text = if (homework.daysBeforeDeadline == 1)
                    itemView.context.getString(R.string.one_day_before_deadline)
                else
                    itemView.context.getString(R.string.day_before_deadline)
                        .format(homework.daysBeforeDeadline)
                if (homework.isUrgent)
                    setTextColor(itemView.context.getColor(R.color.red))
            }
            homeworkTextView.text = homework.homework
            avatarOneImageView.setImageResource(getAvatarImageId(itemView.context, homework.partners[0]))
            avatarTwoImageView.setImageResource(getAvatarImageId(itemView.context, homework.partners[1]))
        }
    }
}