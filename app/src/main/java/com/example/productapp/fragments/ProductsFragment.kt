package com.example.productsapp.fragments

import android.graphics.Color
import android.nfc.Tag
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productapp.R
import com.example.productapp.adapters.ProductAdapter
import com.example.productapp.databinding.FragmentProductsBinding
import com.example.productapp.fragments.DetailFragment
import com.example.productsapp.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment  : Fragment(R.layout.fragment_products ){
    private val viewModel : ProductsViewModel by viewModels()
    private var fragmentBinding : FragmentProductsBinding? = null
    private val adapter by lazy { ProductAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductsBinding.bind(view)
        fragmentBinding = binding
        observeLiveData()
        clickBottomSheet()
        fragmentBinding!!.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

    }

    private fun observeLiveData() {
        with(fragmentBinding!!) {
            viewModel.productsList.observe(viewLifecycleOwner, Observer {
                allProductsBtn.setBackgroundColor(Color.GRAY)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                menBtn.setBackgroundColor(Color.WHITE)
                womenBtn.setBackgroundColor(Color.WHITE)
                adapter.produts = it
                recyclerView.adapter = adapter
            })

            allProductsBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.GRAY)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                menBtn.setBackgroundColor(Color.WHITE)
                womenBtn.setBackgroundColor(Color.WHITE)
                viewModel.productsList.observe(viewLifecycleOwner, Observer { products ->
                        adapter.produts = products
                        recyclerView.adapter = adapter
                })
            }

            electronicsBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.WHITE)
                electronicsBtn.setBackgroundColor(Color.GRAY)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                menBtn.setBackgroundColor(Color.WHITE)
                womenBtn.setBackgroundColor(Color.WHITE)
                viewModel.electronics.observe(viewLifecycleOwner, Observer { electronics ->
                        adapter.produts = electronics
                        recyclerView.adapter = adapter
                })
            }

            jeweleryBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.WHITE)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.GRAY)
                menBtn.setBackgroundColor(Color.WHITE)
                womenBtn.setBackgroundColor(Color.WHITE)
                viewModel.jewelery.observe(viewLifecycleOwner, Observer { jewelery ->
                        adapter.produts = jewelery
                        recyclerView.adapter = adapter

                })
            }

            menBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.WHITE)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                menBtn.setBackgroundColor(Color.GRAY)
                womenBtn.setBackgroundColor(Color.WHITE)
                viewModel.men.observe(viewLifecycleOwner, Observer { men ->
                        adapter.produts = men
                        recyclerView.adapter = adapter
                })
            }

            womenBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.WHITE)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                menBtn.setBackgroundColor(Color.WHITE)
                womenBtn.setBackgroundColor(Color.GRAY)
                viewModel.women.observe(viewLifecycleOwner, Observer { women ->
                        adapter.produts = women
                        recyclerView.adapter = adapter
                })
            }
        }
    }

    private fun clickBottomSheet(){
        adapter.onItemClick = {
            val bottomSheetDialogFragment = DetailFragment()
            bottomSheetDialogFragment.show(requireFragmentManager(),tag)
            viewModel.addToBasket(it)
        }
    }
}