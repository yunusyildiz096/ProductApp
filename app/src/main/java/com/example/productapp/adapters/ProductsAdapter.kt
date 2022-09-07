package com.example.productsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productapp.databinding.ProductsItemBinding
import com.example.productsapp.model.ProductsResponseItem

class ProductsAdapter(val productsList : List<ProductsResponseItem>,private val listener : Listener/*,private val bottomClick : Listener*/) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    class ProductsViewHolder(var binding : ProductsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val recyclerView = ProductsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductsViewHolder(recyclerView)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val products = productsList.get(position)
        holder.binding.apply{
            titleItem.text = products.title
            priceItem.text = "${products.price.toString()}$"
            Glide.with(holder.itemView.context).load(products.image).into(imageItem)
            basketButton.setOnClickListener {
                listener.onItemClick(products)
            }

        }
        holder.itemView.setOnLongClickListener {

            //bottomClick.bottomSheetClick(products)
            listener.bottomSheetClick(products)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }


    interface  Listener{
        fun onItemClick(product: ProductsResponseItem)
        fun bottomSheetClick(product: ProductsResponseItem)
    }

}