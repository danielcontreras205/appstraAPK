package com.example.login.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.login.MainActivity
import com.example.login.R
import com.example.login.data.session.SessionManager
import com.example.login.databinding.FragmentHomeBinding
import com.example.login.databinding.ModalCerrarSesionBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment(),MenuActionHandler  {

    private var _binding: FragmentHomeBinding ? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    //menu por interface
    override fun onCerrarSesionDesdeMenu() {
        mostrarModalCerrarSesion()
    }

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

        val sessionManager = SessionManager(requireContext())
        val token = sessionManager.getToken()
        val user = sessionManager.getUser()
        val companyList = sessionManager.getCompanyList()

        binding.textViewToken.text = "Token: $token"
        binding.textViewUser.text = "Usuario: ${user?.userUser ?: "Sin nombre"}" // Asegúrate que user no sea null
        binding.textViewTipoEstado.text = "Tipo Estado: ${user?.state?.stateType?.stateTypeName ?: "Sin tipo estado"}"
        binding.textViewEstado.text = "Estado: ${user?.state?.stateName ?: "Sin tipo estado"}"
        binding.textViewCompanies.text = if (companyList != null && companyList.isNotEmpty()) {
            "Empresas: ${companyList.joinToString(",") { it.companyId.toString() }}"
        } else {
            "No hay empresas"
        }

        binding.btnCerrarSesion.setOnClickListener {
            mostrarModalCerrarSesion()
        }
    }
    // funciones -----------------------------------------------------------------------------------
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun mostrarModalCerrarSesion() {
        /*
            Recomendación:
            Usa AlertDialog para mensajes y confirmaciones simples.
            Usa DialogFragment para layouts personalizados reutilizables.
            Usa BottomSheetDialog para una experiencia de usuario más moderna tipo "modal deslizable".
            */
        val sessionManager = SessionManager(requireContext())
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val modalBinding = ModalCerrarSesionBinding.inflate(layoutInflater)

        modalBinding.btnConfirmarCerrar.setOnClickListener {
            sessionManager.clearSession()
            bottomSheetDialog.dismiss()
            //no permite regresar al home desde login NavOptions.Builder().setPopUpTo(R.id.homeFragment
            findNavController().navigate(R.id.action_homeFragment_to_LoginFragment,null,NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build())
            //carga el menu
            (activity as? MainActivity)?.actualizarMenu()
            Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show()
        }

        modalBinding.btnCancelar.setOnClickListener {
            //Cierra el modal si está visible.
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.setContentView(modalBinding.root)
        bottomSheetDialog.show()
    }

}
