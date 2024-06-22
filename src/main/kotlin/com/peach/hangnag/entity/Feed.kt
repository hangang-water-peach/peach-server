package com.peach.hangnag.entity

import jakarta.persistence.Column
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

    imageUrl: String = "",
    title: String,
    content: String,
    viewCount: Int = 0,
    likeCount: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
) {
    @Column(length = 2000)
    var imageUrl = imageUrl
        protected set

    @Column(length = 2000)
    var title = title
        protected set

    @Column(length = 2000)
    var content = content
        protected set

    var viewCount = viewCount
        protected set

    var likeCount = likeCount
        protected set

    fun updateFeed(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
