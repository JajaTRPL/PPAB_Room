package com.example.authorlisst.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authorlisst.databinding.ActivityFavoritesBinding
import com.example.authorlist.adapter.ApiAdapter
import com.example.authorlist.database.FavoriteRoomDatabase
import com.example.authorlist.model.ApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var adapter: ApiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi adapter
        adapter = ApiAdapter(mutableListOf(), FavoriteRoomDatabase.getInstance(this).favoriteDao()) {}

        // Atur RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Muat data favorit
        loadFavorites()
    }

    private fun loadFavorites() {
        val favoriteDao = FavoriteRoomDatabase.getInstance(this).favoriteDao()

        lifecycleScope.launch(Dispatchers.IO) {
            val favorites = favoriteDao.getFavoriteCharacters()
            val apiDataList = favorites.map { favorite ->
                ApiData(
                    id = favorite.id,
                    name = favorite.name,
                    status = favorite.status,
                    species = favorite.species,
                    gender = favorite.gender,
                    isFavorite = favorite.isFavorite
                )
            }

            withContext(Dispatchers.Main) {
                adapter.updateData(apiDataList)
            }
        }
    }
}