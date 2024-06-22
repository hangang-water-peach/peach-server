package com.peach.hangnag.controller.dto.request

data class SignUpRequest(
    val code: String,
    val nickName: String,
    val sex: String,
    val age: Int,
)
