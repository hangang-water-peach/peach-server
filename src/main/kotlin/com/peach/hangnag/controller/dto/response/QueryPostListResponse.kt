package com.peach.hangnag.controller.dto.response

data class QueryPostListResponse(
    val postList: List<PostElement>,
)

data class PostElement(
    val title: String,
    val date: String,
    val likeCount: String,
    val userName: String,
)
