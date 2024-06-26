package com.peach.hangnag.controller

import com.peach.hangnag.controller.dto.request.CreateFeedCommentRequest
import com.peach.hangnag.controller.dto.request.CreateNewsCommentRequest
import com.peach.hangnag.controller.dto.request.UpdateCommentRequest
import com.peach.hangnag.service.CommentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/comment")
@RestController
class CommentController(
    private val commentService: CommentService,
) {

    @PostMapping("/news")
    fun createNewsComment(
        @RequestBody request: CreateNewsCommentRequest,
    ) {
        commentService.createNewsComment(request)
    }

    @PostMapping("/feeds")
    fun createFeedComment(
        @RequestBody request: CreateFeedCommentRequest,
    ) {
        commentService.createFeedComment(request)
    }

    @PutMapping("/{comment-id}")
    fun updateComment(
        @RequestBody request: UpdateCommentRequest,
        @PathVariable("comment-id") commentId: Long,
    ) {
        commentService.updateComment(request, commentId)
    }

    @DeleteMapping("/{comment-id}")
    fun deleteComment(
        @PathVariable("comment-id") commentId: Long,
    ) {
        commentService.deleteComment(commentId)
    }
}
