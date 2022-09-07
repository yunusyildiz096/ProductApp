package com.example.productsapp.repo

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productsapp.api.ProductsRetrofit
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.roomdb.BasketDAO
import com.example.productsapp.roomdb.BasketDatabase

class ProductsRepository(application: Application) {

    private val retrofit = ProductsRetrofit()

    val productsBasketList = MutableLiveData<List<ProductsResponseItem>>()
    val getProduct = MutableLiveData<ProductsResponseItem>()

    private val dao = BasketDatabase.getDatabaseInstance(application).basketDao()



    suspend fun getAllProducts() = retrofit.api.getAllProducts()

    suspend fun getElectronics() = retrofit.api.getElectronics()

    suspend fun getJewelery() = retrofit.api.getJewelery()

    suspend fun getMen() = retrofit.api.getMen()

    suspend fun getWomen() = retrofit.api.getWomen()



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
         dao.getBaskets().let {
            productsBasketList.value = it
        }
    }
    suspend fun allDelete(){
        dao.allDelete()
    }



}