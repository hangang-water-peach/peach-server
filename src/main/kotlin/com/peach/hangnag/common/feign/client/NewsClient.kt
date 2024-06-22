package com.peach.hangnag.common.feign.client

import com.peach.hangnag.common.feign.dto.response.NewsResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "NewsClient", url = "https://openapi.naver.com/v1/search/news.json")
interface NewsClient {

    @GetMapping
    fun getNewsBySearchKey(
        @RequestParam("query") queryKey: String,
        @RequestHeader("X-Naver-Client-Id") clientId: String,
        @RequestHeader("X-Naver-Client-Secret") clientSecret: String,
        @RequestParam("display") display: Int = 10,
    ): NewsResponse
}
