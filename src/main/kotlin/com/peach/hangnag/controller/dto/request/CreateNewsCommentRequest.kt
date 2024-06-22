package com.peach.hangnag.controller.dto.request

data class CreateNewsCommentRequest(
    val email: String,
    val content: String,
    val newsId: Long,
)
