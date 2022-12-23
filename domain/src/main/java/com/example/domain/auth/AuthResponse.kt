package com.example.domain.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    @Json(name = "access") val accessToken: String,
    @Json(name = "refresh") val refreshToken: String
)