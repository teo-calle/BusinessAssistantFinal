package com.teo.businessassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance();

        val c = Calendar.getInstance()
        val ano = c.get(Calendar.YEAR)
        val mes = c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)

        iv_calendar.setOnClickListener {
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    tvFecha.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
                }, ano, mes, dia
            )
            dpd.show()
        }

        bt_agregarcliente.setOnClickListener {

            val nombre: String = et_nombreCliente.text.toString()
            val correo: String = et_CorreoCliente.text.toString()
            val contra: String = et_celularCliente.text.toString()
            val rcontra: String = et_direccionCliente.text.toString()
            var genero: String = rb_Maculino.text.toString()
            val fecha: String = tvFecha.text.toString()


            if (rb_Femenino.isChecked) {
                genero = rb_Femenino.text.toString()
            }

            if (nombre.isEmpty() || correo.isEmpty() || contra.isEmpty() || rcontra.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show()
                if (nombre.isEmpty()) {
                    et_nombreCliente.setError("Este campo no puede estar vacio")
                }

                if (correo.isEmpty()) {
                    et_CorreoCliente.setError("Este campo no puede estar vacio")
                }

                if (contra.isEmpty()) {
                    et_celularCliente.setError("Este campo no puede estar vacio")
                }

                if (rcontra.isEmpty()) {
                    et_direccionCliente.setError("Este campo no puede estar vacio")
                }
            }


            mAuth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(
                    this
                ){ task ->
                    if (task.isSuccessful) {
                        crearUsuarioEnBaseDeDatos()
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        onBackPressed()
                    } else {
                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

    }

    private fun crearUsuarioEnBaseDeDatos() {
        TODO("Not yet implemented")
    }
}

private fun AppCompatActivity.onViewCreated(view: View, savedInstanceState: Bundle?) {

}