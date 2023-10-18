package com.gb.schooldiary.domain.entity

data class Class(
    val name: String,
    val description: String?,
    val teacher: String,
    val startTime: String,
    val endTime: String,
    val isBaseClass: Boolean,
    val isHasVideo: Boolean
)
