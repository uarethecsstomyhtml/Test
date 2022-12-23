package com.example.data.auth.mapper

import com.example.domain.auth.AuthResponse
import com.example.domain.auth.AuthUi
import com.example.domain.networkcomponents.Mapper


class AuthMapper : Mapper<AuthResponse, AuthUi> {

    override fun map(input: AuthResponse): AuthUi {
        return AuthUi(accessToken = input.accessToken, refreshToken = input.refreshToken)
    }

}