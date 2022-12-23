package com.example.auth.signin

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.mvi.MviIntent
import com.example.core.mvi.MviViewModel
import com.example.core.mvi.ReduceAction
import com.example.core.mvi.State
import com.example.domain.auth.AuthRepository
import com.example.domain.auth.signin.SignInRequest
import com.example.domain.networkcomponents.NetworkResult
import kotlinx.coroutines.launch


data class SignInState(
    val phone: String = "",
    val password: String = "",
    val enabledNextButton: Boolean = false,
    val requestState: SignInRequestState = SignInRequestState.Idle
): State

sealed class SignInRequestState {
    object Idle : SignInRequestState()
    object Loading : SignInRequestState()
    object Success : SignInRequestState()
    object Error : SignInRequestState()
}

sealed class SignInMviIntent : MviIntent {
    data class PhoneUpdated(val phone: String) : SignInMviIntent()
    data class PasswordUpdated(val password: String) : SignInMviIntent()
    object RequestSignIn : SignInMviIntent()
}

sealed class SignInAction : ReduceAction {
    data class PhoneUpdated(val phone: String, val enabledNextButton: Boolean) : SignInAction()
    data class PasswordUpdated(
        val password: String,
        val enabledNextButton: Boolean
    ) : SignInAction()
    object Loading : SignInAction()
    object Success : SignInAction()
    object Error : SignInAction()
}

class SignInViewModel constructor(
    private val authRepository: AuthRepository
) : MviViewModel<SignInState, SignInMviIntent, SignInAction>(initialState = SignInState()) {

    override suspend fun executeIntent(intent: SignInMviIntent) {
        when(intent) {
            is SignInMviIntent.PhoneUpdated -> {
                handle(
                    SignInAction.PhoneUpdated(
                        phone = intent.phone,
                        enabledNextButton = isNextButtonEnable(phone = intent.phone)
                    )
                )
            }
            is SignInMviIntent.PasswordUpdated -> {
                handle(
                    SignInAction.PasswordUpdated(
                        password = intent.password,
                        enabledNextButton = isNextButtonEnable(phone = intent.password)
                    )
                )
            }
            SignInMviIntent.RequestSignIn -> {
                handle(SignInAction.Loading)
                val request = SignInRequest(phone = state.value.phone, password = state.value.password)
                viewModelScope.launch {
                    when(authRepository.signIn(request = request)) {
                        is NetworkResult.Success -> handle(SignInAction.Success)
                        is NetworkResult.Error -> handle(SignInAction.Error)
                    }
                }
            }
        }
    }

    override fun reduce(state: SignInState, reduceAction: SignInAction): SignInState {
        return when(reduceAction) {
            is SignInAction.PhoneUpdated -> state.copy(
                phone = reduceAction.phone,
                enabledNextButton = reduceAction.enabledNextButton
            )
            is SignInAction.PasswordUpdated -> state.copy(
                password = reduceAction.password,
                enabledNextButton = reduceAction.enabledNextButton
            )
            is SignInAction.Loading -> state.copy(
                requestState = SignInRequestState.Loading
            )
            is SignInAction.Success -> state.copy(
                requestState = SignInRequestState.Success
            )
            is SignInAction.Error -> state.copy(
                requestState = SignInRequestState.Error
            )
        }
    }

    private fun isNextButtonEnable(
        phone: String = state.value.phone,
        password: String = state.value.password
    ): Boolean {
        return phone.isNotEmpty() && password.isNotEmpty()
    }

    fun updatePhone(phone: String) {
        onIntent(SignInMviIntent.PhoneUpdated(phone = phone))
    }

    fun updatePassword(password: String) {
        onIntent(SignInMviIntent.PasswordUpdated(password = password))
    }

    fun signIn() {
        onIntent(SignInMviIntent.RequestSignIn)
    }

}