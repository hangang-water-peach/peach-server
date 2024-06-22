package com.peach.hangnag.controller.dto.response

import java.time.LocalDate

data class QueryDetailResponse(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val createDate: LocalDate,
    val viewCount: String,
    val content: String,
    val commentList: List<CommentElement>,
) {
    data class CommentElement(
        val createDate: String,
        val likeCount: String,
        val userName: String,
    )
}