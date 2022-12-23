package com.example.domain.auth.signin

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignInRequest(
    val phone: String,
    val password: String
)