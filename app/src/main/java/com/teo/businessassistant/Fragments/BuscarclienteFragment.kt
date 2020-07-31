package com.teo.businessassistant.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teo.businessassistant.R
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.teo.businessassistant.model.Cliente
import kotlinx.android.synthetic.main.fragment_buscarcliente.*


class BuscarclienteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buscarcliente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_buscar.setOnClickListener {
            val nombre = et_buscarcliente.text.toString()

            buscarenFirebase(nombre)
        }
    }

    private fun buscarenFirebase(nombre: String) {
        val database=FirebaseDatabase.getInstance()
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        val listaC = user?.uid.toString()
        val myRef=database.getReference(listaC)
        var clienteExiste=false
        val postListener=object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                for(datasnapshot:DataSnapshot in snapshot.children){
                    val cliente=datasnapshot.getValue(Cliente::class.java)
                    if(cliente?.nombre_cliente==nombre){
                        clienteExiste=true
                        mostrarCliente(cliente.nombre_cliente,cliente.correo_cliente,cliente.direccion_cliente,cliente.celular_cliente)

                    }
                }

                if(!clienteExiste)
                    Toast.makeText(requireContext(),"El cliente no existe", Toast.LENGTH_SHORT).show()

                Log.d("data",snapshot.toString())
            }
        }

        myRef.addValueEventListener(postListener)

    }

    private fun mostrarCliente(
        nombreCliente: String,
        correoCliente: String,
        direccionCliente: String,
        celularCliente: String
    ) {
        tv_resultado.text =
            "\nNombre: $nombreCliente\n" +
                    "Celular: $celularCliente\n" +
                    "Dirección: $direccionCliente" +
                    "Correo: $correoCliente"
    }
}






