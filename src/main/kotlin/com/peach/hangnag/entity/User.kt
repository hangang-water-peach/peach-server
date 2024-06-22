package com.peach.hangnag.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val email: String,
    val name: String,

    nickName: String,
    sex: String,
    age: Int
) {
    var nickName = nickName
        protected set
    var sex = sex
        protected set
    var age = age
        protected set

    fun updateProfile(nickName: String, sex: String, age: Int) {
        this.nickName = nickName
        this.sex = sex
        this.age = age
    }
}
