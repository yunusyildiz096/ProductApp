package com.example.productapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productsapp.model.ProductsResponseItem
import com.example.productsapp.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
        private val repository: ProductsRepository
) : ViewModel(){
    fun addToBasket(product : ProductsResponseItem) = viewModelScope.launch {
        repository.insertBasket(product)
    }
}
