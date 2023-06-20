package com.mtg_collection

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDao {
    @Query("SELECT name FROM card")
    fun getAllNames(): List<String>

    @Query("SELECT * FROM card")
    fun loadAll(): List<Card>

    @Query("SELECT * FROM card WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Card

    @Insert
    fun insertAll(vararg cards: Card)

    @Delete
    fun delete(card: Card)

}