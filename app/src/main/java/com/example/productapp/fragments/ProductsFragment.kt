package com.example.productsapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productapp.R
import com.example.productapp.databinding.FragmentProductsBinding
import com.example.productsapp.adapters.ProductsAdapter
import com.example.productsapp.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment  : Fragment(R.layout.fragment_products )/*,ProductsAdapter.Listener */{
    private val viewModel : ProductsViewModel by viewModels()
    var adapter : ProductsAdapter? = null
    private var fragmentBinding : FragmentProductsBinding? = null
    //private var _binding: FragmentProductsBinding? = null
    //private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductsBinding.bind(view)
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
                menBtn.setBackgroundColor(Color.WHITE)
                womenBtn.setBackgroundColor(Color.WHITE)
                adapter = ProductsAdapter(it)
                recyclerView.adapter = adapter
            })



            allProductsBtn.setOnClickListener {
                allProductsBtn.setBackgroundColor(Color.GRAY)
                electronicsBtn.setBackgroundColor(Color.WHITE)
                jeweleryBtn.setBackgroundColor(Color.WHITE)
                menBtn.setBackgroundColor(Color.WHITE)
                womenBtn.setBackgroundColor(Color.WHITE)
                viewModel.productsList.observe(viewLifecycleOwner, Observer { products ->
                        adapter = ProductsAdapter(products)
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
                        adapter = ProductsAdapter(electronics)
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
                        adapter = ProductsAdapter(jewelery)
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
                        adapter = ProductsAdapter(men)
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
                        adapter = ProductsAdapter(women)
                        recyclerView.adapter = adapter

                })
            }


        }
    }


/*
    override fun onItemClick(product: ProductsResponseItem) {
        //viewModel.addToBasket(product)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.detail_product)

        val bottomImage = bottomSheetDialog.findViewById<ImageView>(R.id.detailImage)
        val bottomTitle = bottomSheetDialog.findViewById<TextView>(R.id.title)
        val bottomDescription = bottomSheetDialog.findViewById<TextView>(R.id.detailDescription)
        val bottomCategory = bottomSheetDialog.findViewById<TextView>(R.id.detailCategory)
        val bottomPrice = bottomSheetDialog.findViewById<TextView>(R.id.detailPrice)
        val cancelBottom = bottomSheetDialog.findViewById<TextView>(R.id.cancelTv)
        val addBasket = bottomSheetDialog.findViewById<Button>(R.id.detailAddBasketButton)

        Glide.with(requireContext()).load(product.image).into(bottomImage!!)
        bottomTitle!!.text = product.title
        bottomDescription!!.text = product.description
        bottomCategory!!.text = product.category
        bottomPrice!!.text = product.price.toString()
        cancelBottom!!.setOnClickListener {
            bottomSheetDialog.cancel()
        }
        addBasket!!.setOnClickListener {
            bottomSheetDialog.dismiss()
            viewModel.addToBasket(product)
        }

        bottomSheetDialog.setCancelable(false)
        bottomSheetDialog.show()
    }

    override fun bottomSheetClick(product: ProductsResponseItem) {

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.detail_product)

        val bottomImage = bottomSheetDialog.findViewById<ImageView>(R.id.detailImage)
        val bottomTitle = bottomSheetDialog.findViewById<TextView>(R.id.title)
        val bottomDescription = bottomSheetDialog.findViewById<TextView>(R.id.detailDescription)
        val bottomCategory = bottomSheetDialog.findViewById<TextView>(R.id.detailCategory)
        val bottomPrice = bottomSheetDialog.findViewById<TextView>(R.id.detailPrice)
        val cancelBottom = bottomSheetDialog.findViewById<TextView>(R.id.cancelTv)
        val addBasket = bottomSheetDialog.findViewById<Button>(R.id.detailAddBasketButton)

        Glide.with(requireContext()).load(product.image).into(bottomImage!!)
        bottomTitle!!.text = product.title
        bottomDescription!!.text = product.description
        bottomCategory!!.text = product.category
        bottomPrice!!.text = "${product.price.toString()}$"

        cancelBottom!!.setOnClickListener {
            bottomSheetDialog.cancel()
        }
        addBasket!!.setOnClickListener {
            bottomSheetDialog.dismiss()
            viewModel.addToBasket(product)
        }

        bottomSheetDialog.setCancelable(false)
        bottomSheetDialog.show()
    }

 */
}