package io.github.pdrum.hw2.view.utils

import android.content.Context
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import io.github.pdrum.hw2.R
import io.github.pdrum.hw2.presenter.utils.ProgressIndicator

fun showProgress(context: Context): ProgressIndicator {
    val progressDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
    with(progressDialog) {
        titleText = context.getString(R.string.wait)
        setCancelable(false)
        show()
    }
    return SweetAlertProgressIndicator(progressDialog)
}

private class SweetAlertProgressIndicator(private val wrapped: SweetAlertDialog) :
    ProgressIndicator {
    override fun hide() = wrapped.hide()
}
