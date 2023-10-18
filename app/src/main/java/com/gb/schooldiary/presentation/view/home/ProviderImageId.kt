package com.gb.schooldiary.presentation.view.home

import android.content.Context
import com.gb.schooldiary.R

object ProviderImageId {
    fun getClassImageId(context: Context, nameClass: String): Int =
        when (nameClass) {
            context.getString(R.string.literature) -> R.drawable.literature
            context.getString(R.string.history) -> R.drawable.history
            context.getString(R.string.physics) -> R.drawable.physics
            context.getString(R.string.physical_education) -> R.drawable.physical_education
            context.getString(R.string.biology) -> R.drawable.biology
            context.getString(R.string.mathematics) -> R.drawable.mathematics
            else -> R.drawable.education
        }

    fun getAvatarImageId(context: Context, nameAvatar: String): Int =
        when (nameAvatar) {
            context.getString(R.string.bob) -> R.drawable.bob
            context.getString(R.string.mike) -> R.drawable.mike
            context.getString(R.string.mishel) -> R.drawable.mishel
            else -> R.drawable.avatar
        }
}