package com.example.productsapp.viewmodel

import androidx.lifecycle.*
import com.example.productsapp.model.ProductsResponse
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.model.User
import com.example.productsapp.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
        private val repository: ProductsRepository
) : ViewModel() {

    //var repository = ProductsRepository(application)

    init {
        getAllProducts()
        getElectronics()
        getJewelery()
        getMen()
        getWomen()
    }

    private var _products = MutableLiveData<List<ProductsResponseItem>>()
    val products : LiveData<List<ProductsResponseItem>>
        get() = _products

    private val _response = MutableLiveData<ProductsResponse>()
    val productsList : LiveData<ProductsResponse>
        get() = _response

    private val _electronics = MutableLiveData<ProductsResponse>()
    val electronics : LiveData<ProductsResponse>
        get() = _electronics

    private val _jewelery = MutableLiveData<ProductsResponse>()
    val jewelery : LiveData<ProductsResponse>
        get() = _jewelery

    private val _men = MutableLiveData<ProductsResponse>()
    val men : LiveData<ProductsResponse>
        get() = _men

    private val _women = MutableLiveData<ProductsResponse>()
    val women : LiveData<ProductsResponse>
        get() = _women


    fun getAllProducts() = viewModelScope.launch {
        val response = repository.getAllProducts()
        if (response.isSuccessful){
            _response.postValue(response.body())
        }
    }

    fun getElectronics() = viewModelScope.launch {
        val response = repository.getElectronics()
        if (response.isSuccessful){
            _electronics.postValue(response.body())
        }
    }
    fun getJewelery() = viewModelScope.launch {
        val response = repository.getJewelery()
        if (response.isSuccessful){
            _jewelery.postValue(response.body())
        }
    }
    fun getMen() = viewModelScope.launch {
        val response = repository.getMen()
        if (response.isSuccessful){
            _jewelery.postValue(response.body())
        }
    }
    fun getWomen() = viewModelScope.launch {
        val response = repository.getWomen()
        if (response.isSuccessful){
            _jewelery.postValue(response.body())
        }
    }

    fun addToBasket(product : ProductsResponseItem) = viewModelScope.launch{
        repository.insertBasket(product)
    }

    fun getProducts(){
        repository.getBaskets()
        _products = repository.productsBasketList
    }



}
