package com.peach.hangnag.entity.vo

import java.time.LocalDate

data class CommentListVo(
    val createDate: LocalDate,
    val likeCount: Int,
    val userName: String,
)
