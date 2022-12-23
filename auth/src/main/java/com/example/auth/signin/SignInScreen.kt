package com.example.auth.signin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.auth.signup.SignUpRequestState
import com.example.core.R
import com.example.core.components.button.BaseButton
import com.example.core.components.button.BaseTextButton
import com.example.core.components.text.TextBodyMedium
import com.example.core.components.text.TextTitleLarge
import com.example.core.components.textfield.PasswordTextField
import com.example.core.components.textfield.phone.PhoneTextField
import com.example.core.theme.Dimens
import org.koin.androidx.compose.get

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = get(),
    onNavigateToMain: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    produceState(initialValue = state.requestState, key1 = state.requestState) {
        if (state.requestState is SignInRequestState.Success) {
            onNavigateToMain()
        }
        value = state.requestState
    }

    Column(
        modifier = Modifier.background(color = Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(top = Dimens.padding_extra_huge))
        TextTitleLarge(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding_extra_large),
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(top = Dimens.padding_extra_huge))
        PhoneTextField(
            field = state.phone,
            onFieldChange = { viewModel.updatePhone(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding_large),
            labelRes = R.string.hint_phone_number
        )
        Spacer(modifier = Modifier.padding(top = Dimens.padding_medium))
        PasswordTextField(
            field = state.password,
            onFieldChange = { viewModel.updatePassword(password = it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding_large),
            labelRes = R.string.hint_password
        )
        Spacer(modifier = Modifier.padding(top = Dimens.padding_huge))
        BaseButton(
            onClick = { viewModel.signIn() },
            text = stringResource(R.string.action_sign_in),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding_large),
            enabled = state.enabledNextButton,
            isLoading =  state.requestState is SignInRequestState.Loading
        )
        Spacer(modifier = Modifier.padding(top = Dimens.padding_small))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.login_not_account_caption))
            BaseTextButton(
                onClick = { onNavigateToSignUp() },
                text = stringResource(id = R.string.action_sign_up)
            )
        }

    }
}