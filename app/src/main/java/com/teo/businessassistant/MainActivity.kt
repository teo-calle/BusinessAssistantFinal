package com.teo.businessassistant

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_clientes,
                R.id.navigation_inventario,
                R.id.navigation_compras
                )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        setBottomNavVisibility()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.item1){
            FirebaseAuth.getInstance().signOut()
            goToLoginActivity()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun goToLoginActivity( ){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    /******************************************************************************************************************/
    /***************//*Función para ocultar el buttom navigation al pasar a un fragmento anidado*//********************/
    /******************************************************************************************************************/

    private fun setBottomNavVisibility(){

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.navigation_nuevoproducto -> hideBottomNav() /*******Aquí solo lo oculta estando en nuevo elemento****/
                R.id.navigation_nuevocliente -> hideBottomNav()
                else -> showBottomNav()
            }

           /* when(destination.id){
                R.id.navigation_nuevocliente -> hideBottomNav() /*******Aquí solo lo oculta estando en nuevo cliente****/
            }*/
        }
    }

    private fun showBottomNav(){
        nav_view.visibility = View.VISIBLE
    }

    private fun hideBottomNav(){
        nav_view.visibility = View.GONE
    }

    /*********Ir atras con la flechita***********/
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }
}