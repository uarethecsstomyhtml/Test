package com.example.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val border_zero: Dp,
    val border_medium: Dp,
    val border_large: Dp,
    val button_default_height: Dp,
    val dividerHeight: Dp,
    val padding_extra_tiny: Dp,
    val padding_tiny: Dp,
    val padding_extra_small: Dp,
    val padding_small: Dp,
    val padding_tiny_medium: Dp,
    val padding_medium: Dp,
    val padding_large: Dp,
    val padding_extra_large: Dp,
    val padding_huge: Dp,
    val padding_extra_huge: Dp,
    val text_field_default_height: Dp
)

val smallDimensions = Dimensions(
    border_zero = 0.dp,
    border_medium = 0.5.dp,
    border_large = 1.dp,
    button_default_height = 56.dp,
    dividerHeight = 0.5.dp,
    padding_extra_tiny = 1.dp,
    padding_tiny = 2.dp,
    padding_extra_small = 4.dp,
    padding_small = 8.dp,
    padding_tiny_medium = 12.dp,
    padding_medium = 16.dp,
    padding_large = 24.dp,
    padding_extra_large = 32.dp,
    padding_huge = 40.dp,
    padding_extra_huge = 48.dp,
    text_field_default_height = 56.dp
)

private val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions
}

@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

object AppTheme {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current
}

val Dimens: Dimensions
    @Composable
    get() = AppTheme.dimens