package com.peach.hangnag.service

import com.peach.hangnag.controller.dto.response.CommentElement
import com.peach.hangnag.controller.dto.response.IssueElement
import com.peach.hangnag.controller.dto.response.PostElement
import com.peach.hangnag.controller.dto.response.QueryDetailResponse
import com.peach.hangnag.controller.dto.response.QueryIssueListResponse
import com.peach.hangnag.controller.dto.response.QueryPostListResponse
import com.peach.hangnag.repository.NewsCommentRepository
import com.peach.hangnag.repository.FeedRepository
import com.peach.hangnag.repository.NewsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QueryService(
    private val newsRepository: NewsRepository,
    private val feedRepository: FeedRepository,
    private val newsCommentRepository: NewsCommentRepository,
) {

    fun queryIssueDetail(newsId: Long): QueryDetailResponse {
        val issueDetail = newsRepository.findByIdOrNull(newsId)
            ?: throw RuntimeException()
        val commentList = newsCommentRepository.findAllByNewsId(newsId)

        val response = commentList.map { comment ->
            QueryDetailResponse.CommentElement(
                createDate = comment.createDate,
                likeCount = comment.likeCount,
                userName = comment.userName,
            )
        }

        return QueryDetailResponse(
            imageUrl = issueDetail.imageUrl,
            title = issueDetail.title,
            createDate = issueDetail.createDate,
            viewCount = issueDetail.viewCount.toString(),
            content = issueDetail.content,
            commentList = response,
        )
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
