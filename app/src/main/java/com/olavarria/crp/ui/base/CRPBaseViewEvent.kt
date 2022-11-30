package com.olavarria.crp.ui.base

sealed class CRPBaseViewEvent {

    object ShowLoading : CRPBaseViewEvent()

    object DismissLoading : CRPBaseViewEvent()

    data class ShowErrorMessage(val message: Any, val errorId: Int? = null) : CRPBaseViewEvent()

}
