package com.example.productsapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "basket")
data class ProductsResponseItem(
    @ColumnInfo(name = "category")
    var category: String? = "",
    @ColumnInfo(name = "description")
    var description: String? = "",
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    @ColumnInfo(name = "image")
    var image: String? = "",
    @ColumnInfo(name = "price")
    var price: Double?,
    @ColumnInfo(name = "title")
    var title: String? = "",
): Parcelable

