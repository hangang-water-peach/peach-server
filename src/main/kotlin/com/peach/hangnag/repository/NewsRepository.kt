package com.peach.hangnag.repository

import com.peach.hangnag.entity.News
import com.peach.hangnag.entity.vo.IssueVo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface NewsRepository : CrudRepository<News, Long> {

    @Query(
        """
        SELECT
            n.imageUrl AS imageUrl,
            n.title AS title,
            n.createDate AS date,
            n.viewCount AS viewCount,
            u.nickName AS userName,
            c.content AS comment
        FROM News AS n
        INNER JOIN NewsComment AS c
        ON n.id = c.news.id
        INNER JOIN User AS u
        ON c.user.id = u.id
        WHERE c.id =(
            SELECT c1.id
                FROM FeedComment c1
                ORDER BY c1.likeCount DESC
                LIMIT 1
        )
        ORDER BY n.viewCount DESC
        """
    )
    fun findAllIssueByViewCount(): List<IssueVo>

    @Query(
        """
        SELECT
            n.imageUrl AS imageUrl,
            n.title AS title,
            n.createDate AS date,
            n.viewCount AS viewCount,
            u.nickName AS userName,
            c.content AS comment
        FROM News AS n
        INNER JOIN NewsComment AS c
        ON n.id = c.news.id
        INNER JOIN User AS u
        ON c.user.id = u.id
        WHERE c.id =(
            SELECT c1.id
                FROM FeedComment c1
                ORDER BY c1.likeCount DESC
                LIMIT 1
        )
        ORDER BY n.createDate DESC
        """
    )
    fun findAllIssueByCreateDate(): List<IssueVo>
}
