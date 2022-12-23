package com.example.core.components.image

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun BaseIcon(
    @DrawableRes drawable: Int,
    modifier: Modifier = Modifier,
    tint: Color = Color.White
) {
    Icon(
        painter = painterResource(id = drawable),
        modifier = modifier,
        contentDescription = null,
        tint = tint
    )
}