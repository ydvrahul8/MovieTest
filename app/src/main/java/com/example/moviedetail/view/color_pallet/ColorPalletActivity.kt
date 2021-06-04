package com.example.moviedetail.view.color_pallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.moviedetail.R
import com.example.moviedetail.model.babySize.BabySize
import com.example.moviedetail.model.babySize.Gallery
import com.example.moviedetail.utils.GlideApp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_color_pallet.*
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import kotlinx.android.synthetic.main.fragment_popular_movies.recycler_view


class ColorPalletActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_pallet)
        val data = getData()
        val adapter = ColorPalletAdapter()
        recycler_view.adapter = adapter
        adapter.setData(data.gallery as ArrayList<Gallery>, 1)
    }

    private fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (exception: Exception) {
            exception.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getData(): BabySize {
        val babySize = getJsonDataFromAsset("data.json")

        val babySizeType = object : TypeToken<BabySize>() {}.type

        val gson = Gson()

        return gson.fromJson(babySize, babySizeType)
    }
}