package com.example.productapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.productapp.R
import com.example.productapp.util.downloadFromUrl
import com.example.productapp.util.placeHolderProgressBar
import com.example.productsapp.fragments.ProductsFragmentDirections
import com.example.productsapp.model.ProductsResponseItem

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    //var onItemClick : ((ImageView) -> Unit)? = null
    private val diffUtil = object  : DiffUtil.ItemCallback<ProductsResponseItem>(){
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

    private val recyclerDiffer = AsyncListDiffer(this,diffUtil)
    var produts : List<ProductsResponseItem>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.products_item,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productList = produts.get(position)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageItem)
        val titleText = holder.itemView.findViewById<TextView>(R.id.titleItem)
        val priceText = holder.itemView.findViewById<TextView>(R.id.priceItem)
        //val basketButton = holder.itemView.findViewById<Button>(R.id.basketButton)

        //titleText.text = productList.title
        priceText.text = "${productList.price.toString().toFloat()}$"
        imageView.downloadFromUrl(productList.image, placeHolderProgressBar(holder.itemView.context))
        holder.itemView.setOnClickListener {
            val nav = ProductsFragmentDirections.actionHomeToDetailFragment(productList)
            Navigation.findNavController(it).navigate(nav)
        }

    }

    override fun getItemCount(): Int {
        return  produts.size
    }
}