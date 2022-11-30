package com.olavarria.crp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.olavarria.core.base.adapter.BaseViewHolder
import com.olavarria.core.bindings.executeAfter
import com.olavarria.crp.databinding.ItemPartidoBinding
import com.olavarria.domain.bean.Item
import java.text.SimpleDateFormat


class ItemViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemPartidoBinding>(
    binding = ItemPartidoBinding.inflate(inflater, parent, false)
) {
    fun bind(eventos: Item, id: Int) {
        binding.executeAfter {
            this.item = eventos
            clContenedor.setBackgroundResource(id)
        }
        eventos._createDate?.let {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(it)
            binding.tvFecha.text = SimpleDateFormat("dd-MM-yyyy HH:mm").format(date)
        }
    }
}