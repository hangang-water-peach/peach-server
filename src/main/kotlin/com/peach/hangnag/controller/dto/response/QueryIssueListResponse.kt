package com.peach.hangnag.controller.dto.response

data class QueryIssueListResponse(
    val issueList: List<IssueElement>,
)

data class IssueElement(
    val id: Long,
    val imageUrl: String, // s3 upload 후 return
    val title: String,
    val date: String,
    val viewCount: String,
    val commentElement: CommentElement,
)

data class CommentElement(
    val name: String,
    val content: String,
)
