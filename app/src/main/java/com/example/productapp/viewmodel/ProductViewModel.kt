package com.example.productsapp.viewmodel

import androidx.lifecycle.*
import com.example.productsapp.model.ProductsResponse
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.model.User
import com.example.productsapp.repo.LoginRepository
import com.example.productsapp.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
        private val repository: ProductsRepository
) : ViewModel() {

    init {
        getAllProducts()
        getElectronics()
        getJewelery()
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




    private fun getAllProducts() = viewModelScope.launch {
        val response = repository.getAllProducts()
        if (response.isSuccessful){
            _response.postValue(response.body())
        }
    }

    private fun getElectronics() = viewModelScope.launch {
        val response = repository.getElectronics()
        if (response.isSuccessful){
            _electronics.postValue(response.body())
        }
    }
    private fun getJewelery() = viewModelScope.launch {
        val response = repository.getJewelery()
        if (response.isSuccessful){
            _jewelery.postValue(response.body())
        }
    }
}
