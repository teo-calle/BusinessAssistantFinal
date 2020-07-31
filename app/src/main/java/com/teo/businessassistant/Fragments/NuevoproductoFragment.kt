package com.teo.businessassistant.Fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.teo.businessassistant.R
import com.teo.businessassistant.model.Cliente
import com.teo.businessassistant.model.Producto
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.fragment_nuevoproducto.*
import kotlinx.android.synthetic.main.fragment_nuevoproducto.view.*


class NuevoproductoFragment : Fragment() {

    private val REQUEST_IMAGE_CAPTURE=1234

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevoproducto, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    iv_foto.setOnClickListener {
        dispathTakePictureIntent()
    }
/*Con esto se lleva toda la info al clickear a la base de datos*/
        bt_añadir_elemento.setOnClickListener{
            val nombre_elemento = et_elemento.text.toString()
            val unidad_elemento = et_unit.text.toString().toInt()
            val descripcion_elemento= tv_descripcion.text.toString()
            val precio=et_precio.text.toString().toInt()

            guardarEnFirebase(nombre_elemento,unidad_elemento,descripcion_elemento,precio)


            cleanEditText()
        }
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            val imageBitmap=data?.extras?.get("data") as Bitmap

            iv_foto.setImageBitmap(imageBitmap)
        }
    }

    private fun dispathTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ takePictureIntent->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun guardarEnFirebase(nombre_elemento: String, unidad_elemento: Int, descripcion_elemento: String, precio: Int) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()  /*Instancia de nuestra base de datos*/
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        val listaC = user?.uid.toString()
        val myRef: DatabaseReference =database.getReference(listaC)
        val id :String?=myRef.push().key
        val Producto=Producto(
            id, /* Creamos nuestro objeo*/
            nombre_elemento,
            unidad_elemento,
            descripcion_elemento,
            precio
        )
        myRef.child(id!!).setValue(Producto)
    }

    private fun cleanEditText() {
        et_elemento.setText("")
        et_unit.setText("")
        tv_descripcion.setText("")
        et_precio.setText("")
    }




}








