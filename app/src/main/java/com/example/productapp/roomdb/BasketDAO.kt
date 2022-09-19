package com.example.productsapp.roomdb

import androidx.room.*
import com.example.productsapp.model.ProductsResponseItem

@Dao
interface BasketDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToBasket(products :ProductsResponseItem)

    @Delete
    suspend fun deleteBasket(products : ProductsResponseItem)

    @Query("SELECT * FROM basket")
    fun getBaskets() : List<ProductsResponseItem>

    @Query("SELECT * FROM basket WHERE id = :basketId")
    suspend fun getBasket(basketId : Int) : ProductsResponseItem

    @Query("DELETE FROM basket ")
    suspend fun allDelete()

}