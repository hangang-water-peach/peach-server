package com.peach.hangnag.service

import com.peach.hangnag.controller.dto.response.CommentElement
import com.peach.hangnag.controller.dto.response.IssueElement
import com.peach.hangnag.controller.dto.response.MyPostElement
import com.peach.hangnag.controller.dto.response.PostElement
import com.peach.hangnag.controller.dto.response.QueryDetailResponse
import com.peach.hangnag.controller.dto.response.QueryIssueListResponse
import com.peach.hangnag.controller.dto.response.QueryMyPostListResponse
import com.peach.hangnag.controller.dto.response.QueryPostListResponse
import com.peach.hangnag.repository.FeedCommentRepository
import com.peach.hangnag.repository.FeedRepository
import com.peach.hangnag.repository.NewsCommentRepository
import com.peach.hangnag.repository.NewsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class QueryService(
    private val newsRepository: NewsRepository,
    private val feedRepository: FeedRepository,
    private val newsCommentRepository: NewsCommentRepository,
    private val feedCommentRepository: FeedCommentRepository,
) {

    fun queryIssueDetail(newsId: Long): QueryDetailResponse {
        val issueDetail = newsRepository.findByIdOrNull(newsId)
            ?: throw RuntimeException()
        val commentList = newsCommentRepository.findAllByNewsId(newsId)

        val response = commentList.map { comment ->
            QueryDetailResponse.CommentElement(
                createDate = comment.createDate.toString(),
                likeCount = comment.likeCount.toString(),
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
            id = issueDetail.id,
        )
    }

    fun queryPostDetail(feedId: Long): QueryDetailResponse {
        val postDetail = feedRepository.findByIdOrNull(feedId)
            ?: throw RuntimeException()

        val commentList = feedCommentRepository.findAllByFeedId(feedId)

        val response = commentList.map { comment ->
            QueryDetailResponse.CommentElement(
                createDate = LocalDate.now().toString(),
                likeCount = "1",
                userName = "엄청난 유저",
            )
        }

        return QueryDetailResponse(
            imageUrl = postDetail.imageUrl,
            title = postDetail.title,
            createDate = postDetail.createdAt.toLocalDate(),
            viewCount = postDetail.viewCount.toString(),
            content = postDetail.content,
            commentList = response,
            id = postDetail.id,
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
                id = issue.id,
                imageUrl = issue.imageUrl,
                title = issue.title,
                date = issue.date.toString(),
                viewCount = issue.viewCount.toString(),
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
                id = post.id,
                title = post.title,
                date = post.date,
                likeCount = post.likeCount,
                userName = post.userName,
            )
        }

        return QueryPostListResponse(response)
    }

    fun queryMyPostList(email: String): QueryMyPostListResponse {
        val postList = feedRepository.findAllByUserEmail(email)

        val response = postList.map { post ->
            MyPostElement(
                id = post.id,
                title = post.title,
                date = post.createdAt,
                likeCount = post.likeCount,
                commentCount = 0,
            )
        }

        return QueryMyPostListResponse(response)
    }
}
