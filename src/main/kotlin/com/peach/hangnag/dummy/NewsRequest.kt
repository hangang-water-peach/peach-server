package com.peach.hangnag.dummy

import java.time.LocalDate

data class NewsRequest(
    val createDate: LocalDate,
    val likeCount: Int,
    val viewCount: Int,
    val content: String,
    val imageUrl: String,
    val link: String,
    val title: String,
)
