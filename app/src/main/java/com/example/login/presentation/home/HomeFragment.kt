package com.example.login.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        val menssage = sessionManager.getMessage()
        val companyList = sessionManager.getCompanyList()

        binding.textViewToken.text = "Token: $token"
        binding.textViewUser.text = "Usuario: ${user?.userUser ?: "Sin nombre"}" // Asegúrate que user no sea null
        binding.textViewMessage.text = "Mensaje: $menssage"
        binding.textViewCompanies.text = if (companyList != null && companyList.isNotEmpty()) {
            "Empresas:\n${companyList.joinToString("\n") { it.companyId.toString() }}"
        } else {
            "No hay empresas"
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
