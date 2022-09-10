package com.example.productsapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    //private var repository = ProductsRepository(application)

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