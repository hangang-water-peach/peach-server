package com.peach.hangnag.repository

import com.peach.hangnag.entity.vo.PostVo

interface CustomFeedRepository {
    fun findAllPostOrderByCreatedAt(): List<PostVo>
    fun findAllPostOrderByLikeCount(): List<PostVo>
}
