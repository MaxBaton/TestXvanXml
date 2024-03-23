package com.maxbay.testxvan.ui.utils

import android.content.Context
import android.content.res.Configuration

fun Context.isLandscapeMode(): Boolean = this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE