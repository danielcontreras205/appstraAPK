package com.example.login.presentation.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.login.MainActivity
import com.example.login.R
import com.example.login.data.remote.retrofit.login.RetrofitUsuario
import com.example.login.utils.constants.GeneralPaths
import com.example.login.data.session.SessionManager
import com.example.login.databinding.FragmentLoginBinding
import com.example.login.domain.model.user.ModelCompany
import com.google.android.material.bottomsheet.BottomSheetDialog

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Se coloca el ambiente en el cual va a trabajar
        val sessionManager = SessionManager(requireContext())
        sessionManager.setAmbiente(GeneralPaths.PRODUCCION)
        sessionManager.getAmbiente()?.let { RetrofitUsuario.init(it) }


        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(username, password)
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            handleLoginResult(success, sessionManager)
        }
        // carga datos a sesion
        viewModel.tokenResponse.observe(viewLifecycleOwner){ response ->
            response?.let {
                sessionManager.saveSession(it.token,it.user,it.listCompany,it.messageDTO, null)
                //carga el menu
                (activity as? MainActivity)?.actualizarMenu()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    // Maneja el resultado del intento de login
    private fun handleLoginResult(success: Boolean, sessionManager: SessionManager) {
        if (success) { // Si el login fue exitoso
            val companyList = sessionManager.getCompanyList() // Obtiene la lista de empresas asociadas al usuario

            when {
                companyList.isNullOrEmpty() -> { // Si no hay empresas
                    Toast.makeText(requireContext(), "No se encontraron empresas", Toast.LENGTH_SHORT).show()
                }
                companyList.size == 1 -> { // Si solo hay una empresa
                    sessionManager.setSelectedCompany(companyList[0]) // Establece esa empresa como seleccionada
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment) // Navega al fragmento principal (home)
                    Toast.makeText(requireContext(), sessionManager.getMessage(), Toast.LENGTH_SHORT).show() // Muestra mensaje almacenado en sesión
                }
                else -> {
                    // Si hay más de una empresa, muestra el selector para elegir una
                    mostrarSeleccionEmpresa(companyList, sessionManager)
                }
            }
        } else {
            // Si el login falló, muestra mensaje de error
            Toast.makeText(requireContext(), "Login fallido", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingInflatedId")
// Muestra un modal (BottomSheet) para que el usuario seleccione una empresa
    private fun mostrarSeleccionEmpresa(companyList: List<ModelCompany>, sessionManager: SessionManager) {
        val dialog = BottomSheetDialog(requireContext()) // Crea un diálogo tipo BottomSheet
        val view = layoutInflater.inflate(R.layout.modal_seleccionar_empresa, null) // Infla el layout personalizado del modal

        val spinner: Spinner = view.findViewById(R.id.spinnerOpciones) // Obtiene el spinner del layout
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, // Usa un layout simple de Android para los ítems
            companyList.map { it.companyId } // Extrae los IDs de empresa para mostrar en el spinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Define el layout para el desplegable
        spinner.adapter = adapter // Asocia el adapter al spinner

        // Configura el botón de confirmación dentro del modal
        view.findViewById<Button>(R.id.btnConfirmarEmpresa)?.setOnClickListener {
            val seleccion = companyList[spinner.selectedItemPosition] // Obtiene la empresa seleccionada
            sessionManager.setSelectedCompany(seleccion) // Guarda la empresa seleccionada (opcional, si tienes este método)
            sessionManager.setSelectedCompany(seleccion) // Establece la empresa activa en la sesión
            dialog.dismiss() // Cierra el modal
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment) // Navega al fragmento principal
            Toast.makeText(requireContext(), "Ingresaste a: ${seleccion.companyId}", Toast.LENGTH_SHORT).show() // Muestra mensaje con la empresa
        }

        dialog.setContentView(view) // Establece la vista personalizada al diálogo
        dialog.setCancelable(false) // No permite cancelar el modal tocando fuera de él
        dialog.show() // Muestra el diálogo en pantalla
    }




}


