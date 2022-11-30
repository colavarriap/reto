package com.olavarria.core.base.activity

import androidx.lifecycle.ViewModelProvider
import com.olavarria.core.base.BaseViewModel
import com.olavarria.core.extensions.lazyThreadSafetyNone
import java.lang.reflect.ParameterizedType

abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity() {

    @Suppress("UNCHECKED_CAST")
    protected val viewModel by lazyThreadSafetyNone {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(this)[persistentViewModelClass]
    }

}