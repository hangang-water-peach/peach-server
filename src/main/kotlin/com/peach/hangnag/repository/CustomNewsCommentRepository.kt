package com.peach.hangnag.repository

import com.peach.hangnag.entity.vo.CommentListVo

interface CustomNewsCommentRepository {
    fun findAllByNewsId(newsId: Long): List<CommentListVo>
}