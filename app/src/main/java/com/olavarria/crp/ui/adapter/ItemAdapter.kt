package com.olavarria.crp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olavarria.core.base.adapter.BaseListAdapter
import com.olavarria.crp.R
import com.olavarria.domain.bean.Item

class ItemAdapter(
    context: Context
) : BaseListAdapter<Item>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }
) {
    private var _context = context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ItemViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = getItem(position) as Item
            var id = if (position % 2 == 0) R.drawable.background_rounded_item_par else R.drawable.background_rounded_item_impar
            holder.bind(item, id)
        }
    }

    override fun submitList(list: MutableList<Item>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}

