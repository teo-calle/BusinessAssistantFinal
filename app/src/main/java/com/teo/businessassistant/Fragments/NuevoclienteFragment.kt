package com.teo.businessassistant.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teo.businessassistant.R
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.teo.businessassistant.model.Cliente
import kotlinx.android.synthetic.main.activity_registro.bt_agregarcliente
import kotlinx.android.synthetic.main.activity_registro.et_CorreoCliente
import kotlinx.android.synthetic.main.fragment_nuevocliente.*


class NuevoclienteFragment : Fragment() {

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevocliente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    /*FIREBASE*/
    mostrarMensajeBienvenida()
    /*FIN FIREBASE*/

    /*Con esto se lleva toda la info al clickear a la base de datos*/
    bt_agregarcliente.setOnClickListener{
        val nombre_cliente = et_nombreCliente.text.toString()
        val correo_cliente = et_CorreoCliente.text.toString()
        val celular_cliente= et_celularCliente.text.toString()
        val direccion_cliente=et_direccionCliente.text.toString()

        guardarEnFirebase(nombre_cliente,correo_cliente,celular_cliente,direccion_cliente)


        cleanEditText()
    }
}

private fun mostrarMensajeBienvenida() {
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val user: FirebaseUser? = mAuth.currentUser
    val correo = user?.email

    Toast.makeText(requireContext(), "Bienvenido $correo", Toast.LENGTH_SHORT).show()
}

private fun guardarEnFirebase(nombre_cliente: String, correo_cliente: String, celular_cliente: String, direccion_cliente: String) {
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()  /*Instancia de nuestra base de datos*/
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val user: FirebaseUser? = mAuth.currentUser
    val listaC = user?.uid.toString()
    val myRef: DatabaseReference =database.getReference(listaC)
    val id :String?=myRef.push().key

    val Cliente=Cliente(
        id, /* Creamos nuestro objeo*/
        nombre_cliente  ,
        correo_cliente,
        celular_cliente,
        direccion_cliente
    )
    myRef.child(id!!).setValue(Cliente)
}

private fun cleanEditText() {
    et_nombreCliente.setText("")
    et_CorreoCliente.setText("")
    et_celularCliente.setText("")
    et_direccionCliente.setText("")
}

}







