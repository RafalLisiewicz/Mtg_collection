package com.mtg_collection;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Card::class], version = 1)
abstract class CardDB : RoomDatabase(){
    abstract fun cardDao(): CardDao

    companion object {
        private var instance: CardDB? = null
        fun getInstance(context: Context): CardDB {
            if (instance == null) {
                instance = Room.databaseBuilder(context,CardDB::class.java,"MyCards.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as CardDB
        }
    }
}


