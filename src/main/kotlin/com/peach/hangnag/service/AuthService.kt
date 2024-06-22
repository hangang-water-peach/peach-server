package com.peach.hangnag.service

import com.peach.hangnag.common.feign.client.GoogleInfoClient
import com.peach.hangnag.common.feign.client.GoogleOAuthClient
import com.peach.hangnag.common.feign.dto.request.GoogleCodeRequest
import com.peach.hangnag.controller.dto.request.SignUpRequest
import com.peach.hangnag.controller.dto.response.AuthTokenResponse
import com.peach.hangnag.entity.User
import com.peach.hangnag.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val googleOAuthClient: GoogleOAuthClient,
    private val googleInfoClient: GoogleInfoClient,
    @Value("\${spring.security.oauth2.client.registration.google.client-id}")
    private val clientId: String,
    @Value("\${spring.security.oauth2.client.registration.google.client-secret}")
    private val clientSecret: String,
    @Value("\${spring.security.oauth2.client.registration.google.redirect-uri}")
    private val redirectUri: String,
) {

    fun googleAuth(request: SignUpRequest): AuthTokenResponse {
        val accessToken = googleOAuthClient.googleAuth(
            GoogleCodeRequest(
                code = URLDecoder.decode(request.code, StandardCharsets.UTF_8),
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = redirectUri
            )
        ).accessToken

        val googleInfoResponse = googleInfoClient.getUserInfo(accessToken)

        if (userRepository.findByEmail(googleInfoResponse.email)?.email != null) {
            throw RuntimeException()
        }

        userRepository.save(
            User(
                email = googleInfoResponse.email,
                name = googleInfoResponse.name,
                nickName = request.nickName,
                sex = request.sex,
                age = request.age
            )
        )

        return AuthTokenResponse(
            accessToken, googleInfoResponse.email
        )
    }
}
