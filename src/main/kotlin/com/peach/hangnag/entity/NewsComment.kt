package com.peach.hangnag.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
class NewsComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    val news: News,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    createdAt: LocalDate = LocalDate.now(),

    content: String,
    likeCount: Int = 0,
) {
    var createdAt = createdAt
        protected set

    var content = content
        protected set
    var likeCount = likeCount
        protected set

    fun updateComment(content: String) {
        this.content = content
    }
}
