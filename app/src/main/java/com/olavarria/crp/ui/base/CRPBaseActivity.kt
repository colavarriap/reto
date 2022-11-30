package com.olavarria.crp.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import com.olavarria.core.base.activity.BaseVmDbActivity
import com.olavarria.crp.extensions.collect
import com.olavarria.crp.ui.dialog.LoadingDialog

abstract class CRPBaseActivity<VM : CRPBaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>(), BaseView {

    private val loading: LoadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collect(viewModel.baseEvent, ::onViewEvent)
    }

    override fun onViewEvent(baseViewEvent: CRPBaseViewEvent) {
        when (baseViewEvent) {

            is CRPBaseViewEvent.ShowErrorMessage -> {
                showErrorDialog(baseViewEvent.message, baseViewEvent.errorId)
                Log.d("CRPBaseActivity ->", "ERROR")
            }

            CRPBaseViewEvent.DismissLoading -> {
                dismissLoading()
                Log.d("CRPBaseActivity ->", "DISMISS")
            }

            is CRPBaseViewEvent.ShowLoading -> {
                showLoading()
                Log.d("CRPBaseActivity ->", "LOADING")
            }
        }
    }

    override fun showErrorDialog(message: Any, errorId: Int?) {
        Log.e("TAG", "showErrorDialog: $message" )
        showSnackBar("error servicio: $message")
    }

    override fun showLoading() {
        if(!loading.isShowing) {
            loading.show()
        }
    }

    override fun dismissLoading() {
        loading.dismiss()
    }

    open fun showSoftKeyboard() = (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(currentFocus, 0)

    open fun hideSoftKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    fun Context.dismissKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val windowToken = (this as? Activity)?.currentFocus?.windowToken
        windowToken?.let { imm.hideSoftInputFromWindow(it, 0) }
    }

    open fun showSnackBar(message: String) {
        Snackbar.make(viewBinding.root, message, Snackbar.LENGTH_SHORT)
            .show()
    }

}