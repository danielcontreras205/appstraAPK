package com.example.login

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.example.login.data.session.SessionManager
import com.example.login.domain.model.user.ModelCompany
import com.example.login.presentation.home.MenuActionHandler
import com.example.metodos.SessionValidator
import com.example.metodos.SessionValidatorImpl
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama al método onCreate de la clase padre para iniciar el ciclo de vida del Activity
        setContentView(R.layout.drawer_layout_menu) // Asocia este Activity con el layout XML 'activity_main'

        // Inicializa el Toolbar y lo configura como ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // Configura el toolbar como la barra de acciones del Activity

        // Referencia al DrawerLayout y NavigationView definidos en el XML
        drawerLayout = findViewById(R.id.drawer_layout) // Layout que contiene el menú lateral (drawer)
        navigationView = findViewById(R.id.navigation_view) // Vista que contiene las opciones del menú lateral

        // Crea un toggle (ícono de hamburguesa) para manejar la apertura/cierre del Drawer
        toggle = ActionBarDrawerToggle(
            this, // Contexto del Activity
            drawerLayout, // DrawerLayout a controlar
            toolbar, // Toolbar asociado
            R.string.nav_header_title, // Descripción de apertura para accesibilidad
            R.string.nav_header_desc // Descripción de cierre para accesibilidad
        )

        drawerLayout.addDrawerListener(toggle) // Asocia el toggle al DrawerLayout para que responda al gesto deslizante

        toggle.syncState() // Sincroniza el estado del ícono del toggle con el Drawer (abierto/cerrado)

        // Instancia el SessionManager, que se encarga de manejar la sesión del usuario (guardar token, estado de login, etc.)
        sessionManager = SessionManager(this)

        // Crea un validador de sesión (puede verificar si el usuario está logueado o su sesión es válida)
        val validator: SessionValidator = SessionValidatorImpl()

        // Obtiene el NavHostFragment que contiene los fragments navegables
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Extrae el NavController, el cual se usa para controlar la navegación entre fragments
        val navController = navHostFragment.navController

        if (validator.isTokenValid(sessionManager.getToken())) {
            Toast.makeText(this, "Token válido", Toast.LENGTH_SHORT).show()
            if(sessionManager.getSelectedCompany() == null){
                AlertDialog.Builder(this)
                    .setTitle("Advertencia")
                    .setMessage(
                        "Es posible que al iniciar sesión no haya seleccionado con qué compañía desea ingresar. " +
                        "Por favor, cierre sesión y vuelva a iniciar seleccionando una compañía. " +
                        "Si el problema persiste, es posible que no esté registrado en ninguna empresa asociada."
                    )
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss() // Cierra el diálogo al hacer clic en OK
                    }
                    .setCancelable(false) // Evita que el usuario lo cierre tocando fuera
                    .show()
            }
            // Navega al fragmento principal (home)
            navController.navigate(R.id.homeFragment)
            sessionManager.getSelectedCompany()?.let { ocultarNavPersonSiEsNecesario(it) }
        } else {
            Toast.makeText(this, "Sesión Caducada", Toast.LENGTH_SHORT).show()
            sessionManager.clearSession()
            // Navega al fragmento de login
            navController.navigate(R.id.loginFragment)
        }

        // Escucha el cambio de destinos para mostrar/ocultar barra de acción
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isLoggedIn = sessionManager.getToken() != null

            when (destination.id) {
                R.id.loginFragment -> {
                    // Mostramos ActionBar, pero sin título ni menú
                    supportActionBar?.hide()

                    // Ocultamos el botón hamburguesa y bloqueamos el drawer
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    toggle.isDrawerIndicatorEnabled = false
                    toggle.syncState()
                }
                else -> {
                    supportActionBar?.show()
                    supportActionBar?.title = destination.label

                    if (isLoggedIn) {
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                        toggle.isDrawerIndicatorEnabled = true
                        toggle.syncState()
                    } else {
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                        toggle.isDrawerIndicatorEnabled = false
                        toggle.syncState()
                    }
                }
            }
        }

        // navegacion menu derecho
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> navController.navigate(R.id.homeFragment)
                R.id.nav_person -> navController.navigate(R.id.userFragment)
                R.id.nav_gallery -> Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show()
                /**R.id.nav_logout -> {
                    sessionManager.clearSession()
                    navController.navigate(R.id.loginFragment)
                }*/
            }
            drawerLayout.closeDrawers()
            true
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
    //oculta opciones de menu
    fun ocultarNavPersonSiEsNecesario(company: ModelCompany) {
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val navItem = navView.menu.findItem(R.id.nav_person)
        navItem?.isVisible = company.personId != null
        //Log.d("MainActivity", "¿nav_person existe? ${navItem != null}")
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
                val currentFragment = navHostFragment?.childFragmentManager?.fragments
                    ?.firstOrNull { it.isVisible }

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
    // Opcional: Manejo de botón atrás para cerrar el drawer
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
