package com.example.core.components.topappbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.R
import com.example.core.components.image.BaseIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(title: String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White
        ),
        title = { Text(title) },
        windowInsets = WindowInsets(left = 16.dp),
        navigationIcon = {
            BaseIcon(
                drawable = R.drawable.ic_arrow_back,
                tint = Color(0xFF4db6ac)
            )
        }
    )
}