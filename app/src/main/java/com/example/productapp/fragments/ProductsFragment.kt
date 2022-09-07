package com.example.productsapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.productapp.R
import com.example.productapp.databinding.FragmentProductsBinding
import com.example.productsapp.adapters.ProductsAdapter
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.viewmodel.ProductsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProductsFragment : Fragment(R.layout.fragment_products ) ,ProductsAdapter.Listener {
    private val viewModel : ProductsViewModel by viewModels()
    var adapter : ProductsAdapter? = null
    private var fragmentBinding : FragmentProductsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductsBinding.bind(view)
        fragmentBinding = binding

        fragmentBinding!!.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        observeLiveData()
    }

    private fun observeLiveData(){

        viewModel.productsList.observe(viewLifecycleOwner, Observer {
            fragmentBinding!!.allProductsBtn.setBackgroundColor(Color.GRAY)
            fragmentBinding!!.electronicsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.jeweleryBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.menBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.womenBtn.setBackgroundColor(Color.WHITE)
                adapter = ProductsAdapter(it,this)
                fragmentBinding!!.recyclerView.adapter = adapter
        })



        fragmentBinding!!.allProductsBtn.setOnClickListener {
            fragmentBinding!!.allProductsBtn.setBackgroundColor(Color.GRAY)
            fragmentBinding!!.electronicsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.jeweleryBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.menBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.womenBtn.setBackgroundColor(Color.WHITE)
            viewModel.productsList.observe(viewLifecycleOwner, Observer { products ->
                products.let {
                    adapter = ProductsAdapter(it,this)
                    fragmentBinding!!.recyclerView.adapter = adapter

                }

            })
        }

        fragmentBinding!!.electronicsBtn.setOnClickListener {
            fragmentBinding!!.allProductsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.electronicsBtn.setBackgroundColor(Color.GRAY)
            fragmentBinding!!.jeweleryBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.menBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.womenBtn.setBackgroundColor(Color.WHITE)
            viewModel.electronics.observe(viewLifecycleOwner, Observer { electronics ->
                electronics.let {
                    adapter = ProductsAdapter(it,this)
                    fragmentBinding!!.recyclerView.adapter = adapter
                }
            })
        }
        fragmentBinding!!.jeweleryBtn.setOnClickListener {
            fragmentBinding!!.allProductsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.electronicsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.jeweleryBtn.setBackgroundColor(Color.GRAY)
            fragmentBinding!!.menBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.womenBtn.setBackgroundColor(Color.WHITE)
            viewModel.jewelery.observe(viewLifecycleOwner, Observer { jewelery ->
                jewelery.let {
                    adapter = ProductsAdapter(it,this)
                    fragmentBinding!!.recyclerView.adapter = adapter
                }

            })
        }
        fragmentBinding!!.menBtn.setOnClickListener {
            fragmentBinding!!.allProductsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.electronicsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.jeweleryBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.menBtn.setBackgroundColor(Color.GRAY)
            fragmentBinding!!.womenBtn.setBackgroundColor(Color.WHITE)
            viewModel.men.observe(viewLifecycleOwner, Observer { men ->
                men.let {
                    adapter = ProductsAdapter(it,this)
                    fragmentBinding!!.recyclerView.adapter = adapter
                }

            })
        }

        fragmentBinding!!.womenBtn.setOnClickListener {
            fragmentBinding!!.allProductsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.electronicsBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.jeweleryBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.menBtn.setBackgroundColor(Color.WHITE)
            fragmentBinding!!.womenBtn.setBackgroundColor(Color.GRAY)
            viewModel.women.observe(viewLifecycleOwner, Observer { women ->
                women.let {
                    adapter = ProductsAdapter(it,this)
                    fragmentBinding!!.recyclerView.adapter = adapter
                }
            })
        }
        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            fragmentBinding!!.profileName.text = it.userName
            //fragmentBinding!!.profileEmail.text = it.email

        })


    }



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
}