package com.peach.hangnag.controller

import com.peach.hangnag.controller.dto.request.CreateCommentRequest
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

    @PostMapping("/{feed-id}")
    fun createComment(
        @RequestBody request: CreateCommentRequest,
        @PathVariable("feed-id") feedId: Long,
    ) {
        commentService.createComment(request, feedId)
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
