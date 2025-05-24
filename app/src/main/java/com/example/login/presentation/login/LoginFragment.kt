package com.example.login.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.login.R
import com.example.login.data.remote.retrofit.login.RetrofitClient
import com.example.login.utils.constants.GeneralPaths
import com.example.login.data.session.SessionManager
import com.example.login.databinding.FragmentLoginBinding

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
        sessionManager.getAmbiente()?.let { RetrofitClient.init(it) }


        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(username, password)
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->

            if (success) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Login fallido", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.tokenResponse.observe(viewLifecycleOwner){ response ->
            response?.let {
                sessionManager.saveSession(it.token,it.user,it.listCompany,it.messageDTO)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


