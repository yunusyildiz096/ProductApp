package com.example.productsapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.productapp.MainActivity
import com.example.productapp.R
import com.example.productapp.databinding.FragmentSingUpBinding
import com.example.productsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class SingUpFragment: Fragment(R.layout.fragment_sing_up) {

    private var fragmentBinding : FragmentSingUpBinding? = null
    private val viewModel : LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingUpBinding.bind(view)

        fragmentBinding = binding
        fragmentBinding!!.singUpBtn.setOnClickListener { singUp() }
    }

    private fun singUp() {
        fragmentBinding!!.apply {
            val email = editTextEmail.editText!!.text.toString()
            val password = editTextPassword.editText!!.text.toString()
            val userName = editTextUserName.editText!!.text.toString()
            if (email.isNotEmpty() || password.isNotEmpty() || userName.isNotEmpty()) {
                        val i = Intent(requireActivity(),MainActivity::class.java)
                        startActivity(i)
                        requireActivity().finish()
                        viewModel.singUp(email, password, userName)

            } else {
                Snackbar.make(
                    requireView(),
                    "Please Enter Email, Password, User name",
                    Snackbar.LENGTH_SHORT
                ).setAction("") {

                }.show()
            }


        }
    }
}