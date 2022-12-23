package com.example.domain.auth.signup

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignUpRequest(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val password: String
)