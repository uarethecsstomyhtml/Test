package com.example.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.core.R
import com.example.core.components.button.BaseButton
import com.example.core.components.textfield.BaseTextField
import com.example.core.components.textfield.PasswordTextField
import com.example.core.components.textfield.phone.PhoneTextField
import com.example.core.components.topappbar.BaseTopAppBar
import com.example.core.theme.Dimens
import org.koin.androidx.compose.get


@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = get(),
    onNavigateToMain: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    produceState(initialValue = state.requestState, key1 = state.requestState) {
        if (state.requestState is SignUpRequestState.Success) {
            onNavigateToMain()
        }
        value = state.requestState
    }

    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
        BaseTopAppBar(title = stringResource(com.example.auth.R.string.sign_up_title))
        Spacer(modifier = Modifier.padding(top = Dimens.padding_huge))
        BaseTextField(
            field = state.firstName,
            onFieldChange = { viewModel.updateFirstName(firstName = it) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.padding_large),
            labelRes = R.string.hint_first_name
        )
        Spacer(modifier = Modifier.padding(top = Dimens.padding_medium))
        BaseTextField(
            field = state.lastName,
            onFieldChange = { viewModel.updateLastName(lastName = it) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.padding_large),
            labelRes = R.string.hint_last_name
        )
        Spacer(modifier = Modifier.padding(top = Dimens.padding_medium))
        PhoneTextField(
            field = state.phone,
            onFieldChange = { viewModel.updatePhone(phone = it) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.padding_large),
            labelRes = R.string.hint_phone_number
        )
        Spacer(modifier = Modifier.padding(top = Dimens.padding_medium))
        PasswordTextField(
            field = state.password,
            onFieldChange = { viewModel.updatePassword(password = it) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.padding_large),
            labelRes = R.string.hint_password
        )
        Spacer(modifier = Modifier.padding(top = Dimens.padding_extra_large))
        BaseButton(
            onClick = { viewModel.signUp() },
            text = stringResource(R.string.action_sign_in),
            modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.padding_large),
            enabled = state.enabledNextButton,
            isLoading = state.requestState is SignUpRequestState.Loading
        )
    }
}