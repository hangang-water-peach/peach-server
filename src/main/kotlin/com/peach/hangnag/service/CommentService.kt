package com.peach.hangnag.service

import com.peach.hangnag.controller.dto.request.CreateFeedCommentRequest
import com.peach.hangnag.controller.dto.request.CreateNewsCommentRequest
import com.peach.hangnag.controller.dto.request.UpdateCommentRequest
import com.peach.hangnag.entity.FeedComment
import com.peach.hangnag.entity.NewsComment
import com.peach.hangnag.repository.FeedCommentRepository
import com.peach.hangnag.repository.FeedRepository
import com.peach.hangnag.repository.NewsCommentRepository
import com.peach.hangnag.repository.NewsRepository
import com.peach.hangnag.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository,
    private val feedCommentRepository: FeedCommentRepository,
    private val newsCommentRepository: NewsCommentRepository,
    private val newsRepository: NewsRepository,
) {

    fun createNewsComment(request: CreateNewsCommentRequest) {
        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException()

        val news = newsRepository.findByIdOrNull(request.newsId)
            ?: throw RuntimeException()

        newsCommentRepository.save(
            NewsComment(
                news = news,
                user = user,
                content = request.content,
            )
        )
    }

    fun createFeedComment(request: CreateFeedCommentRequest) {
        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException()

        val feed = feedRepository.findByIdOrNull(request.feedId)
            ?: throw RuntimeException()

        feedCommentRepository.save(
            FeedComment(
                feed = feed,
                user = user,
                content = request.content,
            )
        )
    }

    @Transactional
    fun updateComment(request: UpdateCommentRequest, commentId: Long) {
        val comment = newsCommentRepository.findByIdOrNull(commentId)
            ?: throw RuntimeException()

        comment.updateComment(request.content)
    }

    @Transactional
    fun deleteComment(commentId: Long) {
        val comment = newsCommentRepository.findByIdOrNull(commentId)
            ?: throw RuntimeException()

        newsCommentRepository.delete(comment)
    }
}
