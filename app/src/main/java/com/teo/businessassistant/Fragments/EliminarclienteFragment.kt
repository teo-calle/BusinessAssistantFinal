package com.teo.businessassistant.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teo.businessassistant.R


import android.annotation.SuppressLint
import android.app.AlertDialog

import android.util.Log

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.teo.businessassistant.model.Cliente
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.fragment_clientes.*
import kotlinx.android.synthetic.main.fragment_eliminarcliente.*



class EliminarclienteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eliminarcliente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_borrar.setOnClickListener{
            val nombre= et_nombre4.text.toString()
            borrarenFirebase(nombre)
        }
    }

        private fun borrarenFirebase(nombre:String) {

            val database= FirebaseDatabase.getInstance()
            val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
            val user: FirebaseUser? = mAuth.currentUser
            val listaC = user?.uid.toString()
            val myRef=database.getReference(listaC)
            var clienteExiste=false
            val postListener=object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                }

                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(datasnapshot: DataSnapshot in snapshot.children){
                        val cliente=datasnapshot.getValue(Cliente::class.java)
                        if(cliente?.nombre_cliente==nombre){
                            clienteExiste=true

                            val alertDialog:AlertDialog?=activity?.let{
                                val builder=AlertDialog.Builder(it)
                                builder.apply {
                                    setMessage("Desea eliminar el cliente $nombre?")
                                    setPositiveButton("Aceptar") { dialog,id->
                                        myRef.child(cliente.id!!).removeValue()
                                    }
                                    setNegativeButton("Cancelar"){dialog, id ->
                                    }
                                }
                                builder.create()
                            }
                            alertDialog?.show()

                        }
                    }

                    if(!clienteExiste)
                        Toast.makeText(requireContext(),"El cliente no existe", Toast.LENGTH_SHORT).show()

                    Log.d("data",snapshot.toString())
                }
            }

            myRef.addValueEventListener(postListener)

        }
}
