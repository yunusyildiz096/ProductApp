package com.example.productsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.repo.ProductsRepository
import kotlinx.coroutines.launch

class BasketViewModel(application : Application) : AndroidViewModel(application) {

    private var repository = ProductsRepository(application)

    private var _products = MutableLiveData<List<ProductsResponseItem>>()
    val products : LiveData<List<ProductsResponseItem>>
        get() = _products

    init {
        getProducts()
    }

    fun deleteBasket(product : ProductsResponseItem) = viewModelScope.launch {
        repository.deleteBasket(product)
    }
     fun getProducts(){
        repository.getBaskets()
        _products = repository.productsBasketList
    }
    fun allDelete() = viewModelScope.launch {
        repository.allDelete()
    }
}