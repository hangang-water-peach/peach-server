package com.peach.hangnag.repository

import com.peach.hangnag.entity.NewsComment
import org.springframework.data.repository.CrudRepository

interface NewsCommentRepository : CrudRepository<NewsComment, Long>, CustomNewsCommentRepository
