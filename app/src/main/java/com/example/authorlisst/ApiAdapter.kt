package com.example.authorlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.authorlisst.R
import com.example.authorlisst.databinding.ItemApiBinding
import com.example.authorlist.database.Favorite
import com.example.authorlist.database.FavoriteDao
import com.example.authorlist.model.ApiData
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiAdapter(
    private var apiList: List<ApiData>,
    private val favoriteDao: FavoriteDao,
    private val onFavoriteClicked: (ApiData) -> Unit
) : RecyclerView.Adapter<ApiAdapter.ApiViewHolder>() {

    // Fungsi untuk memperbarui data yang diterima
    fun updateData(newApiData: List<ApiData>) {
        apiList = newApiData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val binding = ItemApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        val apiData = apiList[position]
        holder.bind(apiData)
    }

    override fun getItemCount(): Int = apiList.size

    inner class ApiViewHolder(private val binding: ItemApiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(apiData: ApiData) {
            binding.apply {
                // Mengatur teks untuk item
                tvCharName.text = apiData.name
                tvCharStatus.text = apiData.status
                tvCharSpecies.text = apiData.species
                tvCharGender.text = apiData.gender

                // Menggunakan Picasso untuk memuat gambar
                Picasso.get()
                    .load("https://rickandmortyapi.com/api/character/avatar/${apiData.id}.jpeg")
                    .into(imgChar)

                // Mengatur ikon tombol favorit berdasarkan status isFavorite
                updateFavoriteIcon(apiData)

                // Menangani klik pada tombol favorit
                favoriteIcon.setOnClickListener {
                    // Mengubah status favorit dan memanggil callback untuk update
                    apiData.isFavorite = !apiData.isFavorite
                    onFavoriteClicked(apiData) // Panggil callback untuk memperbarui data di MainActivity
                    updateFavoriteIcon(apiData) // Update ikon favorit
                }
            }
        }

        private fun updateFavoriteIcon(apiData: ApiData) {
            // Mengupdate ikon favorit berdasarkan status isFavorite
            binding.favoriteIcon.setImageResource(
                if (apiData.isFavorite) R.drawable.baseline_favorite_24
                else R.drawable.baseline_favorite_border_24
            )
        }
    }
}
