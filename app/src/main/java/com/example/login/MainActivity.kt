package com.example.login

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.example.login.data.session.SessionManager
import com.example.login.presentation.home.MenuActionHandler
import com.example.metodos.SessionValidator
import com.example.metodos.SessionValidatorImpl

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        sessionManager = SessionManager(this)

        val validator: SessionValidator = SessionValidatorImpl()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (validator.isTokenValid(sessionManager.getToken())) {
            Toast.makeText(this, "Token válido", Toast.LENGTH_SHORT).show()
            // Navega al fragmento principal (home)
            navController.navigate(R.id.homeFragment)
        } else {
            Toast.makeText(this, "Sesión Caducada", Toast.LENGTH_SHORT).show()
            sessionManager.clearSession()
            // Navega al fragmento de login
            navController.navigate(R.id.loginFragment)
        }

        // Escucha el cambio de destinos para mostrar/ocultar barra de acción
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> supportActionBar?.hide()
                else -> {
                    supportActionBar?.show()
                    supportActionBar?.title = destination.label
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (sessionManager.getToken() != null) {
            menuInflater.inflate(R.menu.bar_menu, menu)
            return true
        }
        return false
    }

    fun actualizarMenu() {
        invalidateOptionsMenu()
    }
    // opciones menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "Hola Mundo", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_cerrar_sesion -> {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment) // tu NavHostFragment
                val currentFragment = navHostFragment?.childFragmentManager?.fragments?.firstOrNull()

                if (currentFragment is MenuActionHandler) {
                    currentFragment.onCerrarSesionDesdeMenu()
                    true
                } else {
                    false
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
