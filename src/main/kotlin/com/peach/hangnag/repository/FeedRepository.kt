package com.peach.hangnag.repository

import com.peach.hangnag.entity.Feed
import com.peach.hangnag.entity.vo.PostVo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FeedRepository : CrudRepository<Feed, Long> {
    @Query(
        """
        SELECT
            f.title AS title,
            f.createdAt AS date,
            f.likeCount AS likeCount,
            u.nickName AS userName
        FROM Feed AS f
        INNER JOIN User AS u
        ON f.user.id = u.id
        ORDER BY f.createdAt DESC
        """
    )
    fun findAllPostOrderByCreatedAt(): List<PostVo>

    @Query(
        """
        SELECT
            f.title AS title,
            f.createdAt AS date,
            f.likeCount AS likeCount,
            u.nickName AS userName
        FROM Feed AS f
        INNER JOIN User AS u
        ON f.user.id = u.id
        ORDER BY f.likeCount DESC
        """
    )
    fun findAllPostOrderByLikeCount(): List<PostVo>
}
