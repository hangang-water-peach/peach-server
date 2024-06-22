package com.peach.hangnag.service

import com.peach.hangnag.controller.dto.response.CommentElement
import com.peach.hangnag.controller.dto.response.IssueElement
import com.peach.hangnag.controller.dto.response.PostElement
import com.peach.hangnag.controller.dto.response.QueryDetailResponse
import com.peach.hangnag.controller.dto.response.QueryIssueListResponse
import com.peach.hangnag.controller.dto.response.QueryPostListResponse
import com.peach.hangnag.repository.FeedRepository
import com.peach.hangnag.repository.NewsRepository
import org.springframework.stereotype.Service

@Service
class QueryService(
    private val newsRepository: NewsRepository,
    private val feedRepository: FeedRepository,
) {

    fun queryIssueDetail(newsId: Long): QueryDetailResponse {
        val issueDetail =
    }

    fun queryIssueList(sort: String): QueryIssueListResponse {
        val issueList = when (sort) {
            "RECENT" -> newsRepository.findAllIssueByCreateDate()
            "POPULAR" -> newsRepository.findAllIssueByViewCount()
            else -> throw RuntimeException()
        }

        val response = issueList.map { issue ->
            IssueElement(
                imageUrl = issue.imageUrl,
                title = issue.title,
                date = issue.date,
                viewCount = issue.viewCount,
                commentElement = CommentElement(
                    name = issue.userName,
                    content = issue.comment
                )
            )
        }

        return QueryIssueListResponse(response)
    }

    fun queryPostList(sort: String): QueryPostListResponse {
        val postList = when (sort) {
            "RECENT" -> feedRepository.findAllPostOrderByCreatedAt()
            "POPULAR" -> feedRepository.findAllPostOrderByLikeCount()
            else -> throw RuntimeException()
        }

        val response = postList.map { post ->
            PostElement(
                title = post.title,
                date = post.date,
                likeCount = post.likeCount,
                userName = post.userName,
            )
        }

        return QueryPostListResponse(response)
    }
}
