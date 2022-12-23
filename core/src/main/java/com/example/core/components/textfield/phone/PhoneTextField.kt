package com.example.core.components.textfield.phone

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.core.components.textfield.BaseTextField
import com.example.core.theme.Dimens

private const val PHONE_MAX_LENGTH = 10

@Composable
fun PhoneTextField(
    field: String,
    modifier: Modifier,
    height: Dp = Dimens.text_field_default_height,
    onFieldChange: (phone: String) -> Unit,
    labelRes: Int,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    BaseTextField(
        field = field,
        labelRes = labelRes,
        modifier = modifier,
        height = height,
        onFieldChange = {
            if (it.length <= PHONE_MAX_LENGTH) onFieldChange(it)
        },
        visualTransformation = PhoneNumberTransformation(),
        keyboardOptions = keyboardOptions
    )
}