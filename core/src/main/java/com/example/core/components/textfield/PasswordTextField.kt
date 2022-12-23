package com.example.core.components.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.core.R
import com.example.core.components.image.BaseIcon


@Composable
fun PasswordTextField(
    field: String,
    modifier: Modifier,
    onFieldChange: (password: String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    labelRes: Int
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    BaseTextField(
        field = field,
        onFieldChange = onFieldChange,
        modifier = modifier,
        labelRes = labelRes,
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            val imageRes = when(passwordVisible) {
                true -> R.drawable.ic_visibility
                false -> R.drawable.ic_visibility_off
            }
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                BaseIcon(drawable = imageRes, tint = Color.Black)
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
    )
}
