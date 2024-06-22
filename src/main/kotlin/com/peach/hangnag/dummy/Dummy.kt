package com.peach.hangnag.dummy

import com.peach.hangnag.entity.News
import com.peach.hangnag.repository.NewsRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/dummy")
@RestController
class Dummy(
    private val newsRepository: NewsRepository,
) {

    @PostMapping
    fun save(@RequestBody request: NewsRequest) {
        newsRepository.save(
            News(
                title = request.title,
                content = request.content,
                imageUrl = request.imageUrl,
                viewCount = request.viewCount,
                likeCount = request.likeCount,
                createDate = request.createDate,
                link = request.link
            )
        )
    }
}