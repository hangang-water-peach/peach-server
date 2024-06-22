package com.peach.hangnag.controller.dto.response

import java.time.LocalDateTime

data class QueryMyPostListResponse(
    val postList: List<MyPostElement>,
)

data class MyPostElement(
    val id: Long,
    val title: String,
    val date: LocalDateTime,
    val likeCount: Int,
    val commentCount: Int,
)
