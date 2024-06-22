package com.peach.hangnag.controller

import com.peach.hangnag.controller.dto.response.QueryDetailResponse
import com.peach.hangnag.controller.dto.response.QueryIssueListResponse
import com.peach.hangnag.controller.dto.response.QueryPostListResponse
import com.peach.hangnag.service.QueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/query")
@RestController
class QueryController(
    private val queryService: QueryService,
) {

    @GetMapping("/{news-id}")
    fun queryNewsDetail(@PathVariable("news-id") newsId: Long): QueryDetailResponse {
        return queryService.queryIssueDetail(newsId)
    }

    @GetMapping("/issue")
    fun queryIssueList(@RequestParam("sort") sort: String): QueryIssueListResponse {
        return queryService.queryIssueList(sort)
    }

    @GetMapping("/post")
    fun queryPostList(@RequestParam("sort") sort: String): QueryPostListResponse {
        return queryService.queryPostList(sort)
    }
}
