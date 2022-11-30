package com.olavarria.crp.extensions

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*

fun <T> LifecycleOwner.collect(stateFlow: StateFlow<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        stateFlow.collect { t -> observer(t) }
    }
}

fun <T> LifecycleOwner.collect(sharedFlow: SharedFlow<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        sharedFlow.collect { t -> observer(t) }
    }
}

fun <T> CoroutineScope.collectFlow(flow: Flow<T>, body: suspend (T) -> Unit) {
    flow.onEach { body(it) }
        .launchIn(this)
}

fun <T> LifecycleOwner.observer(liveData: LiveData<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        liveData.observe(this@observer) { t ->
            observer(t)
        }
    }
}

fun <T> LifecycleOwner.observer(liveData: LiveData<T>, lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        liveData.observe(this@observer) { t ->
            if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED)
                observer(t)
        }
    }
}

fun <T> LifecycleOwner.observerOne(liveData: LiveData<T>, observer: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        liveData.observeForever {
            liveData.removeObserver(observer)
            observer(it)
        }
    }
}

fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
    observeForever(object: Observer<T> {
        override fun onChanged(value: T) {
            removeObserver(this)
            observer(value)
        }
    })
}