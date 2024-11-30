package com.example.authorlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {

    // Mengambil daftar karakter favorit
    @Query("SELECT * FROM favorites")
    suspend fun getFavoriteCharacters(): List<Favorite>

    // Menyimpan karakter favorit baru atau memperbarui karakter yang sudah ada
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    // Memperbarui data karakter favorit yang sudah ada
    @Update
    suspend fun updateFavorite(favorite: Favorite)

    // Mengambil karakter favorit berdasarkan ID
    @Query("SELECT * FROM favorites WHERE id = :id LIMIT 1")
    suspend fun getFavoriteById(id: Long): Favorite?

    // Menandai karakter sebagai favorit
    @Query("UPDATE favorites SET is_Favorite = 1 WHERE id = :id")
    suspend fun markAsFavorite(id: Long)

    // Menghapus karakter dari favorit
    @Query("UPDATE favorites SET is_Favorite = 0 WHERE id = :id")
    suspend fun removeFromFavorites(id: Long)
}
