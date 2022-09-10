package com.example.productsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productapp.R
import com.example.productapp.databinding.BasketItemBinding
import com.example.productapp.util.downloadFromUrl
import com.example.productapp.util.placeHolderProgressBar
import com.example.productsapp.model.ProductsResponseItem

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<ProductsResponseItem>() {
        override fun areItemsTheSame(
            oldItem: ProductsResponseItem,
            newItem: ProductsResponseItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductsResponseItem,
            newItem: ProductsResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    val recyclerDiffer = AsyncListDiffer(this,diffUtil)

    var baskets : List<ProductsResponseItem>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.basket_item,parent,false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketList = baskets.get(position)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.basketImage)
        val titleText = holder.itemView.findViewById<TextView>(R.id.basketTitle)
        val priceText = holder.itemView.findViewById<TextView>(R.id.basketPrice)

        imageView.downloadFromUrl(basketList.image, placeHolderProgressBar(holder.itemView.context))
        titleText.text = basketList.title
        priceText.text = "${basketList.price.toString()}$"

    }
    override fun getItemCount(): Int {
        return baskets.size
    }

}