package com.example.productsapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.productsapp.api.ProductsAPI
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.roomdb.BasketDAO
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val dao : BasketDAO,
    private val retrofit: ProductsAPI){



    val productsBasketList = MutableLiveData<List<ProductsResponseItem>>()
    private val getProduct = MutableLiveData<ProductsResponseItem>()

    suspend fun getAllProducts() = retrofit.getAllProducts()

    suspend fun getElectronics() = retrofit.getElectronics()

    suspend fun getJewelery() = retrofit.getJewelery()

    suspend fun insertBasket(products : ProductsResponseItem){
        dao.addToBasket(products)
    }

    suspend fun getBasket(basketId : Int){
        val products = dao.getBasket(basketId)
        getProduct.value = products
    }

    suspend fun deleteBasket(products: ProductsResponseItem){
        dao.deleteBasket(products)
    }

    fun getBaskets(){
        val getBaskets = dao.getBaskets()
        productsBasketList.value = getBaskets
    }

    suspend fun allDelete(){
        dao.allDelete()
    }



}