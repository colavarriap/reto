package com.olavarria.core.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.olavarria.core.base.BaseViewModel

abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseVMActivity<VM>() {

    protected lateinit var viewBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewDataBinding()
    }

    open fun setViewDataBinding() {
        viewBinding = DataBindingUtil.setContentView(this, getLayoutResourceId())
        viewBinding.lifecycleOwner = this
        onInitDataBinding()
    }

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    abstract fun onInitDataBinding()

}