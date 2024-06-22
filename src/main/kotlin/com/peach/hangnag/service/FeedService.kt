package com.peach.hangnag.service

import com.peach.hangnag.controller.dto.request.CreateFeedRequest
import com.peach.hangnag.controller.dto.request.UpdateFeedRequest
import com.peach.hangnag.entity.Feed
import com.peach.hangnag.repository.FeedRepository
import com.peach.hangnag.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FeedService(
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository,
) {

    fun createFeed(request: CreateFeedRequest) {
        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException()

        feedRepository.save(
            Feed(
                title = request.title,
                content = request.content,
                user = user
            )
        )
    }

    @Transactional
    fun deleteFeed(feedId: Long) {
        val feed = feedRepository.findByIdOrNull(feedId)
            ?: throw RuntimeException()

        feedRepository.delete(feed)
    }

    @Transactional
    fun updateFeed(request: UpdateFeedRequest, feedId: Long) {
        val feed = feedRepository.findByIdOrNull(feedId)
            ?: throw RuntimeException()

        feed.updateFeed(
            title = request.title,
            content = request.content
        )
    }
}