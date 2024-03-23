package com.maxbay.studentapp.ui.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import androidx.core.view.WindowInsetsControllerCompat

interface StatusBarColorAttr {
    val attr: Int
}

private fun getStatusBarColor(context: Context, colorAttr: StatusBarColorAttr): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(colorAttr.attr, typedValue, true)
    return typedValue.data
}

fun Activity.setStatusBarColor(
    colorAttr: StatusBarColorAttr,
    isDarkModeIcons: Boolean
) {
    this.window.statusBarColor = getStatusBarColor(context = this, colorAttr = colorAttr)
    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).isAppearanceLightStatusBars = isDarkModeIcons
}

fun Context.isLightTheme() = (this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO