package com.teo.businessassistant.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teo.businessassistant.R
import android.annotation.SuppressLint
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.teo.businessassistant.model.Cliente
import kotlinx.android.synthetic.main.fragment_actualizarcliente.*


class ActualizarclienteFragment : Fragment() {
    var idClienteFirebase:String?=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actualizarcliente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideClienteDatosET()

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        val listaC = user?.uid.toString()
        val database= FirebaseDatabase.getInstance()
        val myRef=database.getReference(listaC)


        bt_buscar2.setOnClickListener {
            val nombre = et_nombre3.text.toString()
            buscarenFirebase(myRef,nombre)
        }

        bt_actualizarcliente.setOnClickListener(){
            actualizarenFirebase(myRef)
            HabilitarwidgetBuscar()

        }
    }

    private fun HabilitarwidgetBuscar() {
        et_nuevotelefono.visibility = View.GONE
        et_nueva_direccion.visibility = View.GONE
        et_correoactual.visibility = View.GONE
        bt_actualizarcliente.visibility=View.GONE
        bt_buscar2.visibility=View.VISIBLE
    }

    private fun actualizarenFirebase(myRef: DatabaseReference) {
        val childUpdate=HashMap<String,Any>()
        childUpdate["correo_cliente"]=et_correoactual.text.toString()
        childUpdate["direccion_cliente"]=et_nueva_direccion.text.toString()
        childUpdate["celular_cliente"]=et_nuevotelefono.text.toString()
        myRef.child(idClienteFirebase!!).updateChildren(childUpdate)
    }

    private fun buscarenFirebase(myRef: DatabaseReference, nombre: String) {
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
                        HabilitarwidgetActualizar()
                        et_nuevotelefono.setText(cliente.celular_cliente)
                        et_nueva_direccion.setText(cliente.direccion_cliente)
                        et_correoactual.setText(cliente.correo_cliente)
                        idClienteFirebase=cliente.id
                    }
                }
                if(!clienteExiste)
                    Toast.makeText(requireContext(),"El cliente no existe", Toast.LENGTH_SHORT).show()

            }
        }

        myRef.addValueEventListener(postListener)
    }

    private fun HabilitarwidgetActualizar() {
        et_nuevotelefono.visibility = View.VISIBLE
        et_nueva_direccion.visibility = View.VISIBLE
        et_correoactual.visibility = View.VISIBLE
        bt_buscar2.visibility=View.GONE
        bt_actualizarcliente.visibility=View.VISIBLE
    }

    private fun hideClienteDatosET() {
        et_nuevotelefono.visibility = View.GONE
        et_nueva_direccion.visibility = View.GONE
        et_correoactual.visibility = View.GONE
        bt_actualizarcliente.visibility=View.GONE
    }

}


