package com.muggle.tiktokcopy.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * @date 2026/2/3 23:52
 * @author muggle
 * @desc
 */
@Composable
fun HorizontalDivider(width: Dp = 0.cdp) {
    Spacer(Modifier.width(width))
}

@Composable
fun VerticalDivider(height: Dp = 0.cdp) {
    Spacer(Modifier.height(height))
}