package com.teo.businessassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    val mAuth: FirebaseAuth =FirebaseAuth.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_registrar.setOnClickListener {
            startActivity(Intent(this,RegistroActivity::class.java))
        }

        bt_iniciarsesion.setOnClickListener{
            val correo=et_CorreoCliente.text.toString()
            val contra=et_Contra.text.toString()

            signInWithFirebase(correo, contra)
        }
    }

    /* Iniciar sesion con firebase*/
    private fun signInWithFirebase(correo: String, contra: String) {
        mAuth.signInWithEmailAndPassword(correo, contra)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    goToMainActivity()
                } else {
                    val message= task.exception!!.message.toString()
                    showMessage(message)
                    Log.w("TAG", "signInWithnEmail:failure", task.getException());
                }
            }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(
            this, msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}