package com.peach.hangnag.repository

import com.peach.hangnag.entity.NewsComment
import com.peach.hangnag.entity.vo.CommentListVo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface NewsCommentRepository : CrudRepository<NewsComment, Long> {
    @Query(
        """
        SELECT
            c.createdAt AS createDate,
            c.likeCount AS likeCount,
            u.nickName AS userName
        FROM NewsComment AS c
        INNER JOIN User AS u
        ON c.user.id = u.id
        WHERE c.newsId = :newsId
        """
    )
    fun findAllByNewsId(newsId: Long): List<CommentListVo>
}
