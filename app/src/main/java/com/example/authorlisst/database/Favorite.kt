package com.example.authorlist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int, // ID unik untuk setiap entitas Favorite

    @ColumnInfo(name = "name")
    val name: String, // Nama karakter

    @ColumnInfo(name = "status")
    val status: String, // Status (misalnya "Alive" atau "Dead")

    @ColumnInfo(name = "species")
    val species: String, // Spesies karakter

    @ColumnInfo(name = "gender")
    val gender: String, // Gender karakter

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false // Menandakan apakah karakter ini disukai (favorit)
)
