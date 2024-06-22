package com.peach.hangnag.repository

import com.peach.hangnag.entity.Comment
import org.springframework.data.repository.CrudRepository

interface CommentRepository : CrudRepository<Comment, Long>
