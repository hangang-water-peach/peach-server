package com.peach.hangnag.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Feed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    title: String,
    content: String,
    likeCount: Int,
    stimulation: Int, // 자극
    positive: Int,
    negative: Int,
) {
    var title = title
        protected set
    var content = content
        protected set
    var likeCount = likeCount
        protected set
    var stimulation = stimulation
        protected set
    var positive = positive
        protected set
    var negative = negative
}