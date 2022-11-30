package com.olavarria.core.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.olavarria.core.base.BaseViewModel

abstract class BaseVmVbActivity<VM : BaseViewModel, VB : ViewBinding> :
    BaseVMActivity<VM>() {

    protected lateinit var viewBinding: VB

    protected abstract fun inflateLayout(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewDataBinding()
    }

    open fun setViewDataBinding() {
        viewBinding = inflateLayout()
        setContentView(viewBinding.root)
        onInitDataBinding()
    }

    abstract fun onInitDataBinding()

}