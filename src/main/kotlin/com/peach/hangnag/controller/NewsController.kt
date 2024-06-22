package com.peach.hangnag.controller

import com.peach.hangnag.common.feign.dto.response.NewsResponse
import com.peach.hangnag.service.NewsCronService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/news")
@RestController
class NewsController(
    private val newsCronService: NewsCronService,
) {

    @GetMapping
    fun getNews(@RequestParam("key") key: String): NewsResponse {
        return newsCronService.newsCron(key)
    }
}
