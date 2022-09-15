package com.example.productsapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productapp.R
import com.example.productapp.adapters.ProductAdapter
import com.example.productapp.databinding.FragmentProductsBinding
import com.example.productsapp.viewmodel.LoginViewModel
import com.example.productsapp.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment  : Fragment(R.layout.fragment_products ){
    private val viewModel : ProductViewModel by viewModels()
    private val loginViewModel : LoginViewModel by viewModels()
    private var fragmentBinding : FragmentProductsBinding? = null
    private val adapter by lazy { ProductAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductsBinding.bind(view)
        loginViewModel.getUser()
        fragmentBinding = binding
        observeLiveData()
        fragmentBinding!!.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

    }

    private fun observeLiveData() {
        with(fragmentBinding!!) {
            viewModel.productsList.observe(viewLifecycleOwner, Observer {
                allProductsBtn.setBackgroundColor(Color.GRAY)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                adapter.produts = it
                recyclerView.adapter = adapter
            })

            allProductsBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.GRAY)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                viewModel.productsList.observe(viewLifecycleOwner, Observer { products ->
                        adapter.produts = products
                        recyclerView.adapter = adapter
                })
            }

            electronicsBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.WHITE)
                electronicsBtn.setBackgroundColor(Color.GRAY)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                viewModel.electronics.observe(viewLifecycleOwner, Observer { electronics ->
                        adapter.produts = electronics
                        recyclerView.adapter = adapter
                })
            }

            jeweleryBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.WHITE)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.GRAY)
                viewModel.jewelery.observe(viewLifecycleOwner, Observer { jewelery ->
                        adapter.produts = jewelery
                        recyclerView.adapter = adapter

                })
            }
            loginViewModel.userInfo.observe(viewLifecycleOwner, Observer {
                profileName.text = "Welcome ${it.userName},"
            })

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}