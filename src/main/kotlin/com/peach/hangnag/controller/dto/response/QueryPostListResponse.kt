package com.peach.hangnag.controller.dto.response

import java.time.LocalDateTime

data class QueryPostListResponse(
    val postList: List<PostElement>,
)

data class PostElement(
    val title: String,
    val date: LocalDateTime,
    val likeCount: Int,
    val userName: String,
)
