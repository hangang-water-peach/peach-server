package com.peach.hangnag.repository

import com.peach.hangnag.entity.QNews
import com.peach.hangnag.entity.QNewsComment
import com.peach.hangnag.entity.QUser
import com.peach.hangnag.entity.vo.IssueVo
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class CustomNewsRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : CustomNewsRepository {

    override fun findAllIssueByViewCount(): List<IssueVo> {
        val qNews = QNews.news
        val qUser = QUser.user
        val qNewsComment = QNewsComment.newsComment

//        val subQuery = queryFactory
//            .select(qNewsComment.id)
//            .from(qNewsComment)
//            .orderBy(qNewsComment.likeCount.desc())
//            .limit(1)

        return queryFactory
            .select(
                qNews.id,
                qNews.imageUrl,
                qNews.title,
                qNews.createDate,
                qNews.viewCount,
                qUser.nickName,
                qNewsComment.content
            )
            .from(qNews)
            .leftJoin(qNewsComment).on(qNews.id.eq(qNewsComment.news.id))
            .leftJoin(qUser).on(qNewsComment.user.id.eq(qUser.id))
//            .where(qNewsComment.id.eq(subQuery.fetchFirst()))
            .orderBy(qNews.viewCount.desc())
            .fetch()
            .map { tuple ->
                IssueVo(
                    id = tuple.get(qNews.id)!!,
                    imageUrl = tuple.get(qNews.imageUrl)!!,
                    title = tuple.get(qNews.title)!!,
                    date = tuple.get(qNews.createDate)!!,
                    viewCount = tuple.get(qNews.viewCount)!!,
                    userName = "엄청난 유저",
                    comment = "엄청난 댓글",
                )
            }
    }

    override fun findAllIssueByCreateDate(): List<IssueVo> {
        val qNews = QNews.news
        val qUser = QUser.user
        val qNewsComment = QNewsComment.newsComment

//        val subQuery = queryFactory
//            .select(qNewsComment.id)
//            .from(qNewsComment)
//            .orderBy(qNewsComment.likeCount.desc())
//            .limit(1)

        return queryFactory
            .select(
                qNews.id,
                qNews.imageUrl,
                qNews.title,
                qNews.createDate,
                qNews.viewCount,
                qUser.nickName,
                qNewsComment.content
            )
            .from(qNews)
            .leftJoin(qNewsComment).on(qNews.id.eq(qNewsComment.news.id))
            .leftJoin(qUser).on(qNewsComment.user.id.eq(qUser.id))
//            .where(qNewsComment.id.eq(subQuery.fetchFirst()))
            .orderBy(qNews.viewCount.desc())
            .fetch()
            .map { tuple ->
                IssueVo(
                    id = tuple.get(qNews.id)!!,
                    imageUrl = tuple.get(qNews.imageUrl)!!,
                    title = tuple.get(qNews.title)!!,
                    date = tuple.get(qNews.createDate)!!,
                    viewCount = tuple.get(qNews.viewCount)!!,
                    userName = "엄청난 유저",
                    comment = "엄청난 댓글"
                )
            }
    }
}
