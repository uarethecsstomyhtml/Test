package com.example.auth.di

import com.example.auth.signin.SignInViewModel
import com.example.auth.signup.SignUpViewModel
import com.example.data.auth.remote.AuthApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import java.util.*


val authViewModelModule = module {
    viewModel { SignInViewModel(authRepository = get()) }
    viewModel { SignUpViewModel(authRepository = get()) }
}