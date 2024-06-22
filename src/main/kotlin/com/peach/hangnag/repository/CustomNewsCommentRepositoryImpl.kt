package com.peach.hangnag.repository

import com.peach.hangnag.entity.QNewsComment
import com.peach.hangnag.entity.QUser
import com.peach.hangnag.entity.vo.CommentListVo
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class CustomNewsCommentRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CustomNewsCommentRepository {

    override fun findAllByNewsId(newsId: Long): List<CommentListVo> {
        val qNewsComment = QNewsComment.newsComment
        val qUser = QUser.user

        return queryFactory
            .select(
                Projections.bean(
                    CommentListVo::class.java,
                    qNewsComment.createdAt.`as`("createDate"),
                    qNewsComment.likeCount.`as`("likeCount"),
                    qUser.nickName.`as`("userName")
                )
            )
            .from(qNewsComment)
            .innerJoin(qUser).on(qNewsComment.user.id.eq(qUser.id))
            .where(qNewsComment.news.id.eq(newsId))
            .fetch()
    }
}
