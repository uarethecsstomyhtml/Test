package com.example.core.components.button

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.core.components.image.BaseIcon
import com.example.core.components.loader.BaseLoader
import com.example.core.theme.Dimens

@Composable
fun BaseButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = Dimens.button_default_height,
    contentColor: Color = Color.White,
    @DrawableRes icon: Int? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        modifier = modifier.height(height = height),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2F73DA),
            contentColor = contentColor,
            disabledContentColor = Color(0X993C3C43)
        ),
        enabled = enabled
    ) {
        icon?.let {
            BaseButtonIcon(icon = icon)
        }
        Log.d("isLoading", isLoading.toString())
        when(isLoading) {
            true -> BaseLoader()
            false -> Text(
                text = text,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                fontSize = 17.sp
            )
        }

    }
}

@Composable
private fun BaseButtonIcon(@DrawableRes icon: Int) {
    BaseIcon(
        drawable = icon,
        modifier = Modifier.padding(end = Dimens.padding_small),
        tint = Color.White
    )
}