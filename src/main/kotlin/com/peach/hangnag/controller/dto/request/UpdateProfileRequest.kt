package com.peach.hangnag.controller.dto.request

data class UpdateProfileRequest(
    val email: String,
    val nickName: String,
    val sex: String,
    val age: Int,
)
