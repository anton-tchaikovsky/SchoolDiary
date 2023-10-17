package com.gb.schooldiary.domain

data class Homework(
    val name:String,
    val homework: String,
    val date: String,
    val partners: List<String>,
    var daysBeforeDeadline: Int = 0,
    var isUrgent: Boolean = false
)
