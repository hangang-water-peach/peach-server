package com.peach.hangnag.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class News(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    title: String,
    content: String,
    imageUrl: String = "",
    viewCount: Int = 0,
    likeCount: Int = 0,
    createDate: LocalDate,
    link: String,
) {
    var title = title
        protected set
    var content = content
        protected set

    var imageUrl = imageUrl
        protected set

    var viewCount = viewCount
        protected set

    var likeCount = likeCount
        protected set

    var createDate = createDate
        protected set
    var link = link
        protected set
}
