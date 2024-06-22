package com.peach.hangnag.controller

import com.peach.hangnag.controller.dto.request.UpdateProfileRequest
import com.peach.hangnag.service.UserService
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService
) {

    @PutMapping
    fun updateProfile(@RequestBody request: UpdateProfileRequest) {
        userService.updateProfile(request)
    }
}
