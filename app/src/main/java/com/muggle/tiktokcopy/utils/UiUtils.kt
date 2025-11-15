package com.muggle.tiktokcopy.utils

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.muggle.tiktokcopy.ui.theme.APP_DESIGN_WIDTH

/**
 * 屏幕适配dp
 */
val Number.cdp
    get() = Dp(toFloat()* Resources.getSystem().displayMetrics.widthPixels/ APP_DESIGN_WIDTH/ Resources.getSystem().displayMetrics.density)

/**
 * compose屏幕适配单位（字体专用）
 */
val Number.csp
    get() = (toFloat() *
            Resources.getSystem().displayMetrics.widthPixels
            / APP_DESIGN_WIDTH
            / Resources.getSystem().displayMetrics.scaledDensity).sp
