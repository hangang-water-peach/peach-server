package com.peach.hangnag.controller

import com.peach.hangnag.controller.dto.request.CreateFeedRequest
import com.peach.hangnag.controller.dto.request.UpdateFeedRequest
import com.peach.hangnag.service.FeedService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/feed")
@RestController
class FeedController(
    private val feedService: FeedService,
) {

    @PostMapping
    fun createFeed(@RequestBody request: CreateFeedRequest) {
        feedService.createFeed(request)
    }

    @PutMapping("/{feed-id}")
    fun updateFeed(@RequestBody request: UpdateFeedRequest, @PathVariable("feed-id") feedId: Long) {
        feedService.updateFeed(request, feedId)
    }

    @DeleteMapping("/{feed-id}")
    fun deleteFeed(@PathVariable("feed-id") feedId: Long) {
        feedService.deleteFeed(feedId)
    }
}
