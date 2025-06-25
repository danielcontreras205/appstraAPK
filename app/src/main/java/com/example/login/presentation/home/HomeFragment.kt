package com.example.login.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.example.login.R
import com.example.login.data.session.SessionManager
import com.example.login.databinding.FragmentHomeBinding
import com.example.login.utils.constants.BaseFragmentConMenu
import com.google.android.material.navigation.NavigationView

class HomeFragment : BaseFragmentConMenu()  {

    private var _binding: FragmentHomeBinding ? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val sessionManager = SessionManager(requireContext())
        val token = sessionManager.getToken()
        val user = sessionManager.getUser()
        val companyList = sessionManager.getCompanyList()
        val company = sessionManager.getSelectedCompany()

        binding.textViewToken.text = "Token: $token"
        binding.textViewUser.text = "Usuario: ${user?.userUser ?: "Sin nombre"}" // Asegúrate que user no sea null
        binding.textViewTipoEstado.text = "Tipo Estado: ${user?.state?.stateType?.stateTypeName ?: "Sin tipo estado"}"
        binding.textViewEstado.text = "Estado: ${user?.state?.stateName ?: "Sin tipo estado"}"
        binding.textViewCompanies.text = if (companyList != null && companyList.isNotEmpty()) {
            "Empresas: ${companyList.joinToString(",") { it.companyId.toString() }}"
        } else {
            "No hay empresas"
        }
        binding.textViewCompanie.text = "Compañia: ${company?.companyId ?: "Sin compañia"}"
        // carga informacion del usuario
        user?.let {getPersona(user.userId,"Bearer " + token.toString())}
        // Configurar botones de UI
        configurarEventosUI()
        // Configurar gestos del sistema
        configurarGestosSistema()
    }
    // funciones -----------------------------------------------------------------------------------
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun configurarEventosUI() {
        binding.btnCerrarSesion.setOnClickListener {
            mostrarModalCerrarSesion()
        }
    }
    private fun configurarGestosSistema() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            mostrarModalCerrarSesion()
        }
    }


    private fun getPersona(userId: Int,token: String) {
        viewModel.getPerson(userId,token)
        viewModel.personResponse.observe(viewLifecycleOwner){ response ->
            response?.let {
                // Obtener la activity y luego el NavigationView
                val navigationView = requireActivity().findViewById<NavigationView>(R.id.nav_view) // ID del NavigationView
                val nameTextView = navigationView.findViewById<TextView>(R.id.textView) // Subtitulo
                val titleTextView = navigationView.findViewById<TextView>(R.id.textViewTitle) // Título

                // Asigna los valores recibidos al TextView
                titleTextView.text = it.personFirstName // Ajusta al nombre real del campo
                nameTextView.text = it.personEmail // Ajusta al nombre real del campo
            }
        }
    }
}
