package com.peach.hangnag.service

import com.peach.hangnag.controller.dto.request.CreateCommentRequest
import com.peach.hangnag.controller.dto.request.UpdateCommentRequest
import com.peach.hangnag.entity.Comment
import com.peach.hangnag.repository.CommentRepository
import com.peach.hangnag.repository.FeedRepository
import com.peach.hangnag.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository,
    private val commentRepository: CommentRepository,
) {

    fun createComment(request: CreateCommentRequest, feedId: Long) {
        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException()

        val feed = feedRepository.findByIdOrNull(feedId)
            ?: throw RuntimeException()

        commentRepository.save(
            Comment(
                feedId = feed.id,
                newsId = request.newsId,
                user = user,
                content = request.content,
            )
        )
    }

    @Transactional
    fun updateComment(request: UpdateCommentRequest, commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw RuntimeException()

        comment.updateComment(request.content)
    }

    @Transactional
    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw RuntimeException()

        commentRepository.delete(comment)
    }
}
