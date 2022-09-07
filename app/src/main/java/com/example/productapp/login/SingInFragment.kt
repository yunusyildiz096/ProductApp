package com.example.productsapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.productapp.MainActivity
import com.example.productapp.R
import com.example.productapp.databinding.FragmentSingInBinding
import com.example.productsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class SingInFragment : Fragment(R.layout.fragment_sing_in){

    private var fragmentBinding : FragmentSingInBinding? = null
    val viewModel : LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingInBinding.bind(view)
        fragmentBinding = binding

        binding.singInBtn.setOnClickListener {
            val i = Intent(requireActivity(), MainActivity::class.java)
            startActivity(i)
        }

        fragmentBinding!!.singInBtn.setOnClickListener { singIn() }
    }

    private fun singIn() {
        fragmentBinding!!.apply {
            val email = editTextEmail.editText!!.text.toString()
            val password = editTextPassword.editText!!.text.toString()
            if (email.isNotEmpty() || password.isNotEmpty()){
                viewModel.singIn(email,password)
                val i = Intent(requireActivity(),MainActivity::class.java)
                startActivity(i)
                requireActivity().finish()
            }else{
                Snackbar.make(requireView(), "Please Enter Email, Password", Snackbar.LENGTH_SHORT).setAction("") {

                }.show()
            }
        }

    }

}