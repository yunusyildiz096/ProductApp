package com.example.productsapp.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productapp.R
import com.example.productapp.databinding.FragmentBasketProductsBinding
import com.example.productsapp.adapters.BasketAdapter
import com.example.productsapp.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketProducts : Fragment(R.layout.fragment_basket_products){

    var fragmentBinding : FragmentBasketProductsBinding? = null
    private val viewModel : BasketViewModel by viewModels()
    private val adapter by lazy { BasketAdapter() }

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPositions = viewHolder.layoutPosition
            val selectBasket = adapter.baskets[layoutPositions]
            viewModel.deleteBasket(selectBasket)
            findNavController().navigate(BasketProductsDirections.actionBasketSelf())
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBasketProductsBinding.bind(view)
        fragmentBinding = binding
        observeLiveData()
        dialog()
        viewModel.getProducts()

        fragmentBinding!!.startShoppingBtn.setOnClickListener { startShoppingBtn(it) }
        fragmentBinding!!.paymentLinearLayout.visibility = View.GONE
        fragmentBinding!!.shopCartLinearLayout.visibility = View.VISIBLE
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(fragmentBinding!!.basketRecyclerView)

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.basket_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


            when(item.itemId){
                R.id.delete ->{
                var dialogBinding = layoutInflater.inflate(R.layout.delete_dialog,null)
                val myDialog = Dialog(requireContext())
                myDialog.setContentView(dialogBinding)

                myDialog.setCancelable(false)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()

                val yesBtn = dialogBinding.findViewById<Button>(R.id.yesBtn)
                val noBtn = dialogBinding.findViewById<Button>(R.id.noBtn)
                yesBtn.setOnClickListener {
                    viewModel.allDelete()
                    findNavController().navigate(BasketProductsDirections.actionBasketProductsToProductsFragment())
                    myDialog.cancel()
                }
                noBtn.setOnClickListener {
                    myDialog.cancel()
                }

            }
        }
        return true
    }

    private fun observeLiveData(){
        fragmentBinding!!.basketRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.products.observe(viewLifecycleOwner, Observer { basket ->
            basket.let {
                adapter.baskets = it
                fragmentBinding!!.basketRecyclerView.adapter = adapter
                var total = 0f
                for(i in it){
                    i.price?.let {
                        total += it.toFloat()
                    }
                    fragmentBinding!!.paymentLinearLayout.visibility = View.VISIBLE
                    fragmentBinding!!.shopCartLinearLayout.visibility = View.GONE
                }
                fragmentBinding!!.totalBasketTv.text = "${total}$"

            }
        })
    }
  private fun dialog(){
      fragmentBinding!!.paymentBtn.setOnClickListener {
          var dialogBinding = layoutInflater.inflate(R.layout.payment_dialog,null)
          val myDialog = Dialog(requireContext())
          myDialog.setContentView(dialogBinding)

          myDialog.setCancelable(false)
          myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
          myDialog.show()

          val okBtn = dialogBinding.findViewById<Button>(R.id.okBtn)
          okBtn.setOnClickListener {
              viewModel.allDelete()
              findNavController().navigate(BasketProductsDirections.actionBasketProductsToProductsFragment())
              myDialog.cancel()
          }
      }

      if(activity is AppCompatActivity){
          (activity as AppCompatActivity).setSupportActionBar(fragmentBinding!!.toolBar)
      }
      fragmentBinding!!.toolBar.setNavigationOnClickListener {

          findNavController().navigate(BasketProductsDirections.actionBasketProductsToProductsFragment())
      }


  }

    private fun startShoppingBtn(view: View){
        findNavController().navigate(BasketProductsDirections.actionBasketProductsToProductsFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }

}