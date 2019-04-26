package io.github.pdrum.hw2.view.utils

import android.content.Context
import android.graphics.Color
import com.muddzdev.styleabletoast.StyleableToast

fun toastErr(context: Context, text: String) = StyleableToast
    .Builder(context)
    .backgroundColor(Color.RED)
    .text(text)
    .show()

fun toastErr(context: Context, stringID: Int) = toastErr(context, context.getString(stringID))
