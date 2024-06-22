package com.peach.hangnag.service

import com.peach.hangnag.controller.dto.request.UpdateProfileRequest
import com.peach.hangnag.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    @Transactional
    fun updateProfile(request: UpdateProfileRequest) {
        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException()

        user.updateProfile(
            nickName = request.nickName,
            sex = request.sex,
            age = request.age
        )
    }
}
