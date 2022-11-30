package com.olavarria.crp.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.olavarria.crp.R
import com.olavarria.crp.databinding.DialogLoadingBinding

class LoadingDialog (
    context: Context
) : Dialog(context, R.style.FullScreenDialog) {

    private val binding: DialogLoadingBinding =
        DialogLoadingBinding.inflate(LayoutInflater.from(context))

    init {
        setContentView(binding.root)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}