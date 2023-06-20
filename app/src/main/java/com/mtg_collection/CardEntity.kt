package com.mtg_collection

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card(
    @PrimaryKey val multiverseId: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val set: String,
    @ColumnInfo val rarity: String,
    @ColumnInfo val type: String,
    @ColumnInfo val power: String,
    @ColumnInfo val cost: String,
    @ColumnInfo val description: String
)