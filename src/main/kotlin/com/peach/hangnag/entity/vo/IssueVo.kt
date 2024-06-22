package com.peach.hangnag.entity.vo

import java.time.LocalDate

data class IssueVo(
    val imageUrl: String,
    val title: String,
    val date: LocalDate,
    val viewCount: Int,
    val userName: String,
    val comment: String,
)
