package com.peach.hangnag.controller

import com.peach.hangnag.controller.dto.request.SignUpRequest
import com.peach.hangnag.controller.dto.response.AuthTokenResponse
import com.peach.hangnag.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/signup")
    fun googleAuth(@RequestBody request: SignUpRequest): AuthTokenResponse {
        return authService.googleAuth(request)
    }
}
