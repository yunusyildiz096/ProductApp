package com.example.productapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.productapp.R
import com.example.productapp.databinding.FragmentProfileBinding
import com.example.productapp.login.LoginActivity
import com.example.productsapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile){
    private var fragmentBinding: FragmentProfileBinding? = null
    private val viewModel : LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)
        fragmentBinding = binding
        viewModel.getUser()

        binding.singOutBtn.setOnClickListener {
            viewModel.singOut().also {
                val i = Intent(requireActivity(),LoginActivity::class.java)
                startActivity(i)
                requireActivity().finish()
            }
        }

        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            fragmentBinding!!.userEmailTv.text = it.email
            fragmentBinding!!.userNameTv.text = it.userName
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}