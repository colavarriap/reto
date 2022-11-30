package com.olavarria.core.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.olavarria.core.base.adapter.BaseListAdapter
import com.olavarria.core.base.adapter.ListAdapterItem

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as BaseListAdapter<ListAdapterItem>?
    adapter?.submitList(list?.toMutableList())
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: ViewPager2, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as BaseListAdapter<ListAdapterItem>?
    adapter?.submitList(list?.toMutableList())
}
