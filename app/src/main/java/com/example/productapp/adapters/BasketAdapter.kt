package com.example.productsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productapp.databinding.BasketItemBinding
import com.example.productsapp.model.ProductsResponseItem

class BasketAdapter(val basketList : List<ProductsResponseItem>) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(var binding : BasketItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val recyclerView = BasketItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketViewHolder(recyclerView)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basket = basketList.get(position)
        holder.binding.apply {
            basketTitle.text = basket.title
            basketPrice.text = "${basket.price.toString()}$"
            Glide.with(holder.itemView.context).load(basket.image).into(basketImage)
        }


    }
    override fun getItemCount(): Int {
        return basketList.size
    }

}