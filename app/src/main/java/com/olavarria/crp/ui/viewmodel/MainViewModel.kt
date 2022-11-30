package com.olavarria.crp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.olavarria.core.common.Status
import com.olavarria.core.di.preferences.Preferences
import com.olavarria.core.di.preferences.model.UserModel
import com.olavarria.core.extensions.doOnStatusResult
import com.olavarria.core.extensions.doOnSuccess
import com.olavarria.core.extensions.doOnSuccessResult
import com.olavarria.crp.ui.base.CRPBaseViewModel
import com.olavarria.domain.bean.Item
import com.olavarria.domain.usecases.GetEventsSearchLocalUseCase
import com.olavarria.domain.usecases.GetListEventsLocalUseCase
import com.olavarria.domain.usecases.GetListEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: Preferences,
    private val getListEventsUseCase: GetListEventsUseCase,
    private val getListEventsLocalUseCase: GetListEventsLocalUseCase,
    private val getEventsSearchLocalUseCase: GetEventsSearchLocalUseCase
) : CRPBaseViewModel() {

    private val _usuario = MutableLiveData<UserModel?>()
    val usuario: LiveData<UserModel?> = _usuario

    private val _partidosResult : MutableStateFlow<MutableList<Item>?> = MutableStateFlow(arrayListOf())
    val partidosResult: StateFlow<MutableList<Item>?> get() = _partidosResult

    init {
        _usuario.postValue(preferences.getUser())

        getListEventsLocalUseCase(Unit)
            .doOnSuccess {
                _partidosResult.value = it
            }
            .launchIn(viewModelScope)

        getListaEventos()
    }

    fun getListaEventos(filter: Map<String, String> = hashMapOf()) {

        initStatusState(Status.Loading)
        getListEventsUseCase(GetListEventsUseCase.GetListEventsParams(filter), viewModelScope) {
            it.doOnSuccessResult { events ->
                //if (!events.items.isNullOrEmpty())
                    //_partidosResult.value = events.items!!.toMutableList()
            }.doOnStatusResult { status ->
                initStatusState(status)
            }
        }
    }

    fun getListaEventosBuscar(search: String) {
        getEventsSearchLocalUseCase(GetEventsSearchLocalUseCase.GetEventsSearchLocalParams(search))
            .doOnSuccess {
                _partidosResult.value = it
            }
            .launchIn(viewModelScope)
    }


    fun logout() {
        preferences.setSession(false)
    }

}