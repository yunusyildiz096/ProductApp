package com.example.productsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productsapp.model.User
import com.example.productsapp.repo.LoginRepository

class LoginViewModel(application: Application) :  AndroidViewModel(application){
    private var repository = LoginRepository(application)


    private var _userInfo = MutableLiveData<User>()
    val userInfo : LiveData<User>
        get() = _userInfo

    fun signUp(email : String,password : String,userName : String){
        repository.signUp(email, password,userName)
    }

    fun signIn(email : String,password : String){
        repository.signIn(email,password)
    }

    fun signOut(){
        repository.signOut()
    }

    fun getUser(){
        repository.getUser()
        _userInfo = repository.userInfo
    }
}