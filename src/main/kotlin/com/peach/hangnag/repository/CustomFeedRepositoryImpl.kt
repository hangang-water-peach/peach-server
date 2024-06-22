package com.peach.hangnag.repository

import com.peach.hangnag.entity.QFeed
import com.peach.hangnag.entity.QUser
import com.peach.hangnag.entity.vo.PostVo
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class CustomFeedRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CustomFeedRepository {

    override fun findAllPostOrderByCreatedAt(): List<PostVo> {
        val qFeed = QFeed.feed
        val qUser = QUser.user

        val a = queryFactory
            .select(
                qFeed.id,
                qFeed.title,
                qFeed.createdAt,
                qFeed.likeCount,
                qUser.nickName
            )
            .from(qFeed)
            .innerJoin(qUser)
            .on(qUser.id.eq(qFeed.user.id))
            .fetch()
            .map { tuple ->
                PostVo(
                    id = tuple.get(qFeed.id)!!,
                    title = tuple.get(qFeed.title)!!,
                    date = tuple.get(qFeed.createdAt)!!,
                    likeCount = tuple.get(qFeed.likeCount)!!,
                    userName = "엄청난 유저"
                )
            }

        return a
    }

    override fun findAllPostOrderByLikeCount(): List<PostVo> {
        val qFeed = QFeed.feed
        val qUser = QUser.user

        return queryFactory
            .select(
                qFeed.id,
                qFeed.title,
                qFeed.createdAt,
                qFeed.likeCount,
                qUser.nickName,
            )
            .from(qFeed)
            .leftJoin(qUser)
            .on(qUser.id.eq(qFeed.user.id))
            .orderBy(qFeed.likeCount.desc())
            .fetch()
            .map { tuple ->
                PostVo(
                    id = tuple.get(qFeed.id)!!,
                    title = tuple.get(qFeed.title)!!,
                    date = tuple.get(qFeed.createdAt)!!,
                    likeCount = tuple.get(qFeed.likeCount)!!,
                    userName = "엄청난 유저"
                )
            }
    }
}
