package com.peach.hangnag.controller.dto.request

data class CreateFeedCommentRequest(
    val email: String,
    val content: String,
    val feedId: Long,
)
