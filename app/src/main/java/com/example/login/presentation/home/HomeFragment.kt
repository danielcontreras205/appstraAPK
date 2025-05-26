package com.example.login.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.data.session.SessionManager
import com.example.login.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

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
            sessionManager.clearSession()
            findNavController().navigate(R.id.action_homeFragment_to_LoginFragment)
            Toast.makeText(requireContext(), "Sesion Cerrada", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
