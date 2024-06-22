package com.peach.hangnag.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class Feed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    isIssue: Boolean, // true면 issue, false면 post
    title: String,
    content: String,
    viewCount: Int = 0,
    likeCount: Int = 0,
    stimulation: Int = 0, // 자극
    positive: Int = 50,
    negative: Int = 50,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
) {
    var isIssue = isIssue
        protected set
    var title = title
        protected set
    var content = content
        protected set

    var viewCount = viewCount
        protected set

    var likeCount = likeCount
        protected set
    var stimulation = stimulation
        protected set
    var positive = positive
        protected set
    var negative = negative
        protected set

    fun updateFeed(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
