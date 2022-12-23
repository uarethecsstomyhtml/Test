package com.example.auth.signup

import androidx.lifecycle.viewModelScope
import com.example.core.mvi.MviIntent
import com.example.core.mvi.MviViewModel
import com.example.core.mvi.ReduceAction
import com.example.core.mvi.State
import com.example.domain.auth.AuthRepository
import com.example.domain.auth.signup.SignUpRequest
import com.example.domain.networkcomponents.NetworkResult
import kotlinx.coroutines.launch


data class SignUpState(
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val password: String = "",
    val enabledNextButton: Boolean = false,
    val requestState: SignUpRequestState = SignUpRequestState.Idle
): State

sealed class SignUpRequestState {
    object Idle : SignUpRequestState()
    object Loading : SignUpRequestState()
    object Success : SignUpRequestState()
    object Error : SignUpRequestState()
}

sealed class SignUpMviIntent : MviIntent {
    data class FirstNameUpdated(val firstName: String) : SignUpMviIntent()
    data class LastNameUpdated(val lastName: String) : SignUpMviIntent()
    data class PhoneUpdated(val phone: String) : SignUpMviIntent()
    data class PasswordUpdated(val password: String) : SignUpMviIntent()
    object RequestSignUp : SignUpMviIntent()
}

sealed class SignUpAction : ReduceAction {
    data class FirstNameUpdated(val firstName: String, val enabledNextButton: Boolean) : SignUpAction()
    data class LastNameUpdated(val lastName: String, val enabledNextButton: Boolean) : SignUpAction()
    data class PhoneUpdated(val phone: String, val enabledNextButton: Boolean) : SignUpAction()
    data class PasswordUpdated(
        val password: String,
        val enabledNextButton: Boolean
    ) : SignUpAction()
    object Loading : SignUpAction()
    object Success : SignUpAction()
    object Error : SignUpAction()
}

class SignUpViewModel(
    private val authRepository: AuthRepository
) : MviViewModel<SignUpState, SignUpMviIntent, SignUpAction>(initialState = SignUpState()) {

    override suspend fun executeIntent(intent: SignUpMviIntent) {
        when(intent) {
            is SignUpMviIntent.FirstNameUpdated -> {
                handle(
                    SignUpAction.FirstNameUpdated(
                        firstName = intent.firstName,
                        enabledNextButton = isNextButtonEnable(phone = intent.firstName)
                    )
                )
            }
            is SignUpMviIntent.LastNameUpdated -> {
                handle(
                    SignUpAction.LastNameUpdated(
                        lastName = intent.lastName,
                        enabledNextButton = isNextButtonEnable(phone = intent.lastName)
                    )
                )
            }
            is SignUpMviIntent.PhoneUpdated -> {
                handle(
                    SignUpAction.PhoneUpdated(
                        phone = intent.phone,
                        enabledNextButton = isNextButtonEnable(phone = intent.phone)
                    )
                )
            }
            is SignUpMviIntent.PasswordUpdated -> {
                handle(
                    SignUpAction.PasswordUpdated(
                        password = intent.password,
                        enabledNextButton = isNextButtonEnable(phone = intent.password)
                    )
                )
            }
            SignUpMviIntent.RequestSignUp -> {
                handle(SignUpAction.Loading)
                val request = SignUpRequest(
                    firstName = state.value.firstName,
                    lastName = state.value.lastName,
                    phone = state.value.phone,
                    password = state.value.password
                )
                viewModelScope.launch {
                    when(authRepository.signUp(request = request)) {
                        is NetworkResult.Success -> handle(SignUpAction.Success)
                        is NetworkResult.Error -> handle(SignUpAction.Error)
                    }

                }
            }
        }
    }

    override fun reduce(state: SignUpState, reduceAction: SignUpAction): SignUpState {
        return when(reduceAction) {
            is SignUpAction.FirstNameUpdated -> state.copy(
                firstName = reduceAction.firstName,
                enabledNextButton = reduceAction.enabledNextButton
            )
            is SignUpAction.LastNameUpdated -> state.copy(
                lastName = reduceAction.lastName,
                enabledNextButton = reduceAction.enabledNextButton

            )
            is SignUpAction.PhoneUpdated -> state.copy(
                phone = reduceAction.phone,
                enabledNextButton = reduceAction.enabledNextButton,
            )
            is SignUpAction.PasswordUpdated -> state.copy(
                password = reduceAction.password,
                enabledNextButton = reduceAction.enabledNextButton
            )
            is SignUpAction.Loading -> state.copy(
                requestState = SignUpRequestState.Loading
            )
            is SignUpAction.Success -> state.copy(
                requestState = SignUpRequestState.Success
            )
            is SignUpAction.Error -> state.copy(
                requestState = SignUpRequestState.Error
            )
        }
    }

    private fun isNextButtonEnable(
        firstName: String = state.value.firstName,
        lastName: String = state.value.lastName,
        phone: String = state.value.phone,
        password: String = state.value.password
    ): Boolean {
        return firstName.isNotEmpty()
                && lastName.isNotEmpty()
                && phone.isNotEmpty()
                && password.isNotEmpty()
    }

    fun updateFirstName(firstName: String) {
        onIntent(SignUpMviIntent.FirstNameUpdated(firstName = firstName))
    }

    fun updateLastName(lastName: String) {
        onIntent(SignUpMviIntent.LastNameUpdated(lastName = lastName))
    }

    fun updatePhone(phone: String) {
        onIntent(SignUpMviIntent.PhoneUpdated(phone = phone))
    }

    fun updatePassword(password: String) {
        onIntent(SignUpMviIntent.PasswordUpdated(password = password))
    }

    fun signUp() {
        onIntent(SignUpMviIntent.RequestSignUp)
    }
}