package com.example.productsapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productsapp.model.ProductsResponseItem

@Database(entities = [ProductsResponseItem::class], version = 2, exportSchema = false)
abstract class BasketDatabase  : RoomDatabase(){
    abstract fun basketDao() : BasketDAO
}