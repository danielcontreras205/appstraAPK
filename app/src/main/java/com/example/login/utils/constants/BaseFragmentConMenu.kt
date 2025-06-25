package com.example.login.utils.constants

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.login.MainActivity
import com.example.login.R
import com.example.login.data.session.SessionManager
import com.example.login.databinding.ModalCerrarSesionBinding
import com.example.login.presentation.home.MenuActionHandler
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BaseFragmentConMenu : Fragment(), MenuActionHandler {

    override fun onCerrarSesionDesdeMenu() {
        mostrarModalCerrarSesion()
    }

    protected fun mostrarModalCerrarSesion() {
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
            findNavController().navigate(
                R.id.action_global_loginFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
            )
            (activity as? MainActivity)?.actualizarMenu()
            Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show()
        }

        modalBinding.btnCancelar.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(modalBinding.root)
        bottomSheetDialog.show()
    }
}
