package com.olavarria.crp.ui.activities

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.olavarria.core.bindings.executeAfter
import com.olavarria.crp.R
import com.olavarria.crp.databinding.ActivityMainBinding
import com.olavarria.crp.extensions.collect
import com.olavarria.crp.extensions.observer
import com.olavarria.crp.ui.adapter.ItemAdapter
import com.olavarria.crp.ui.base.CRPBaseActivity
import com.olavarria.crp.ui.utils.setFilterConfig
import com.olavarria.crp.ui.viewmodel.MainViewModel
import com.olavarria.domain.bean.Item
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class MainActivity : CRPBaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getLayoutResourceId() = R.layout.activity_main

    lateinit var itemAdapter: ItemAdapter
    lateinit var listaEvents: MutableList<Item>
    private var filterMaps = HashMap<String, String>()

    override fun onInitDataBinding() {
        observer(viewModel.usuario, this) { model ->
            model?.let {
                viewBinding.executeAfter {
                    this.item = model
                }
            }
        }

        collect(viewModel.partidosResult) { items ->
            items?.let {
                itemAdapter.submitList(items)
                filterMaps.clear()
            }
        }

        with(viewBinding) {

            listaEvents = arrayListOf()

            itemAdapter = ItemAdapter(this@MainActivity).apply {
                submitList(listaEvents)
            }

            rvListaEventos.apply {
                adapter = itemAdapter
            }

            btnCerrarSesion.setOnClickListener {
                viewModel.logout()
            }

            edBuscar.addTextChangedListener {
                if (edBuscar.text.toString().isNotEmpty() || edBuscar.isFocused)
                    viewModel.getListaEventosBuscar(edBuscar.text.toString())
            }

            edBuscar.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    filterMaps = setFilterConfig(edBuscar.text.toString())
                    edBuscar.setText("")
                    viewModel.getListaEventos(filterMaps)
                    true
                } else
                    false
            }

            setFilter(btnReset)
            setFilter(btnFilterTeams)
            setFilter(btnFilterByStatus)
            setFilter(btnSortBy)
            setFilter(btnSortByDesc)

        }

    }

    fun setFilter(tv: TextView) {
        tv.setOnClickListener{
            with(viewBinding) {
                if (tv.tag.toString() == "reset"){
                    edBuscar.setText("")
                    hideSoftKeyboard()
                    viewModel.getListaEventos()
                } else {
                    showSoftKeyboard()
                    if (edBuscar.text.isEmpty()) {
                        edBuscar.setText("?" + tv.tag.toString())
                    } else {
                        if (!edBuscar.text.toString().contains("?")) {
                            edBuscar.setText("")
                            edBuscar.setText("?" + tv.tag.toString())
                        } else {
                            edBuscar.setText(edBuscar.text.toString() + "&" + tv.tag.toString())
                        }
                    }
                    edBuscar.setSelection(edBuscar.text.length)
                }

            }
        }
    }

}