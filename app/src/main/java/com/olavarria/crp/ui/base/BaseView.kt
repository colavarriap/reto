package com.olavarria.crp.ui.base

interface BaseView {

    fun onViewEvent(baseViewEvent: CRPBaseViewEvent)

    fun showErrorDialog(message: Any, errorId: Int? = null)

    fun showLoading()

    fun dismissLoading()

}