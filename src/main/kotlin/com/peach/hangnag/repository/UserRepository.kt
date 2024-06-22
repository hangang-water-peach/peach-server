package com.peach.hangnag.repository

import com.peach.hangnag.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
}
