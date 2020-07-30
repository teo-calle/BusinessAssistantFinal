package com.teo.businessassistant.ui.Inventario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.teo.businessassistant.R
import kotlinx.android.synthetic.main.fragment_inventario.*

class InventarioFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inventario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /***************//*Boton flotante para ir a un nuevo elemento*//*********************/
        btf_adicionar.setOnClickListener {
            findNavController().navigate(R.id.next_to_nuevoproducto)
        }
    }

}