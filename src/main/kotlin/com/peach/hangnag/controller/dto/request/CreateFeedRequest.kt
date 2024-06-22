package com.peach.hangnag.controller.dto.request

data class CreateFeedRequest(
    val email: String,
    val title: String,
    val content: String,
)
