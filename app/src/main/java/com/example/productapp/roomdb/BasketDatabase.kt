package com.example.productsapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productsapp.model.ProductsResponseItem

@Database(entities = [ProductsResponseItem::class], version = 2, exportSchema = false)
abstract class BasketDatabase  : RoomDatabase(){
    abstract fun basketDao() : BasketDAO

    companion object {

        @Volatile
        var INSTANCE: BasketDatabase? = null



        fun getDatabaseInstance(context: Context): BasketDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInstance = Room.databaseBuilder(
                    context, BasketDatabase::class.java,
                    "basket"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = roomDatabaseInstance
                return return roomDatabaseInstance
            }
        }

    }
}