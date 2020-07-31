package com.teo.businessassistant.ui.Clientes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.teo.businessassistant.R
import kotlinx.android.synthetic.main.fragment_clientes.*
import kotlinx.android.synthetic.main.fragment_inventario.*

class ClientesFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_clientes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_nuevo.setOnClickListener {
            findNavController().navigate(R.id.next_to_nuevocliente)
        }
    }
}