package com.peach.hangnag.repository

import com.peach.hangnag.entity.Feed
import org.springframework.data.repository.CrudRepository

interface FeedRepository : CrudRepository<Feed, Long>, CustomFeedRepository {
    fun findAllByUserEmail(email: String): List<Feed>
}
