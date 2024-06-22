package com.peach.hangnag.repository

import com.peach.hangnag.entity.FeedComment
import org.springframework.data.repository.CrudRepository

interface FeedCommentRepository : CrudRepository<FeedComment, Long> {
    fun findAllByFeedId(feedId: Long): List<FeedComment>
}