package com.peach.hangnag.controller.dto.request

data class UpdateFeedRequest(
    val email: String,
    val title: String,
    val content: String,
)
