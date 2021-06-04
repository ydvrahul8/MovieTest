package com.example.moviedetail.view.color_pallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetail.databinding.ItemColorPalletBinding
import com.example.moviedetail.model.babySize.Gallery

class ColorPalletAdapter :
    RecyclerView.Adapter<ColorPalletAdapter.PhotoViewHolder>() {
    private var list = ArrayList<Gallery>()
    private var code = 0
    fun setData(list: ArrayList<Gallery>, code: Int) {
        this.list = list
        this.code = code
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemColorPalletBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(list[position], code)
    }

    class PhotoViewHolder(private val binding: ItemColorPalletBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gallery: Gallery, code: Int) {
            binding.gallery = gallery
        }
    }

    override fun getItemCount(): Int = list.size
}