package com.mdo.yoni.eshop.data.models.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.mdo.yoni.eshop.data.models.Item

@Dao
interface ItemDao {

    @Query("SELECT * from items")
    fun getAll(): List<Item>

    @Query("SELECT * from items where id = :id")
    fun get(id: String): Item

    @Query("SELECT * from items where incompare=1 and incart=0 and discarded=0")
    fun getAllInCompare(): List<Item>

    @Query("SELECT * from items where incart=1 and (discarded=0)")
    fun getAllInCart(): List<Item>

    @Insert(onConflict = REPLACE)
    fun update(item: Item)

    @Delete()
    fun delete(weatherData: Item)

    @Query("DELETE from items")
    fun deleteAll()

}