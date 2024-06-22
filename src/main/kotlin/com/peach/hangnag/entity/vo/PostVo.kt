package com.peach.hangnag.entity.vo

import java.time.LocalDateTime

data class PostVo(
    val title: String,
    val date: LocalDateTime,
    val likeCount: Int,
    val userName: String,
)
