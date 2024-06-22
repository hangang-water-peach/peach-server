package com.peach.hangnag.common.feign.dto.response

data class NewsResponse(
    val items: List<NewsElement>,
)

data class NewsElement(
    val title: String,
    val originallink: String,
    val link: String,
    val description: String,
    val pubDate: String,
)
