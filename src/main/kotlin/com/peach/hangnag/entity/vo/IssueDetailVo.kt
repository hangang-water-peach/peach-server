package com.peach.hangnag.entity.vo

import java.time.LocalDate

data class IssueDetailVo(
    val imageUrl: String,
    val title: String,
    val createDate: LocalDate,
    val viewCount: Int,
    val content: String,
)
