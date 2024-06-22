package com.peach.hangnag.common.feign.client

import com.peach.hangnag.common.feign.dto.request.GoogleCodeRequest
import com.peach.hangnag.common.feign.dto.response.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
interface GoogleOAuthClient {

    @PostMapping(produces = ["application/json"])
    fun googleAuth(googleCodeRequest: GoogleCodeRequest): TokenResponse

}
