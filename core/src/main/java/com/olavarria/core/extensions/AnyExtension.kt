package com.olavarria.core.extensions

import android.content.Context

fun Any.getString(context: Context): String {
    return when (this) {
        is String -> {
            this
        }
        is Int -> {
            context.getString(this)
        }
        else -> {
            ""
        }
    }
}
