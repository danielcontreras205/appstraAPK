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

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        sessionManager = SessionManager(this)

        // navega al main
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // El NavController es el responsable de gestionar la navegación entre fragmentos.
        val navController = navHostFragment.navController
        //escucha el nueva actividad de navegacion
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                // Si el destino es el fragmento de login, oculta la barra de acciones para dar una vista limpia.
                R.id.loginFragment -> supportActionBar?.hide()
                else -> {
                    // Para cualquier otro destino, muestra la barra de acciones y cambia su título al label del destino.
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
