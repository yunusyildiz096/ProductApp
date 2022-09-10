package com.example.productsapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.productsapp.api.ProductsAPI
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.roomdb.BasketDAO
import javax.inject.Inject

class ProductsRepository @Inject constructor(
        val dao : BasketDAO,
        val retrofit: ProductsAPI) {

 //   private val retrofit = ProductsRetrofit()

    val productsBasketList = MutableLiveData<List<ProductsResponseItem>>()
    val getProduct = MutableLiveData<ProductsResponseItem>()

    //private val dao = BasketDatabase.getDatabaseInstance(application).basketDao()



    suspend fun getAllProducts() = retrofit.getAllProducts()

    suspend fun getElectronics() = retrofit.getElectronics()

    suspend fun getJewelery() = retrofit.getJewelery()

    suspend fun getMen() = retrofit.getMen()

    suspend fun getWomen() = retrofit.getWomen()



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
        productsBasketList.value = getBaskets!!
        /*
         dao.getBaskets().let {
            productsBasketList.value = it
        }
         */
    }
    suspend fun allDelete(){
        dao.allDelete()
    }

}