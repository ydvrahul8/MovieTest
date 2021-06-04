package com.example.moviedetail.utils

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.PaletteAsyncListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.moviedetail.R


@BindingAdapter("imageResource")
fun setImageResource(view: ImageView, imageUrl: String?) {
    Glide.with(view)
        .load("https://image.tmdb.org/t/p/w500$imageUrl")
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_error)
        .into(view)

}

@BindingAdapter("imageColor")
fun setImageColor(view: ImageView, imageUrl: String?) {
       GlideApp.with(view)
        .asBitmap()
        .load(imageUrl)
        .centerCrop()
        .error(R.drawable.ic_error)
        .listener(object : RequestListener<Bitmap?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap?>?,
                isFirstResource: Boolean
            ): Boolean {
                view.background
                return false
            }

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap?>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.let {
                    Palette.from(it)
                        .generate(PaletteAsyncListener { palette ->
//                            val textSwatch = palette!!.vibrantSwatch
//                            val textSwatch = palette!!.lightVibrantSwatch
                            //val textSwatch = palette!!.darkMutedSwatch
//                            val textSwatch = palette!!.darkVibrantSwatch
//                            val textSwatch = palette!!.dominantSwatch
                            val textSwatch = palette!!.mutedSwatch
                            if (textSwatch == null) {
                                Toast.makeText(view.context, "Null swatch :(", Toast.LENGTH_SHORT)
                                    .show()
                                return@PaletteAsyncListener
                            }
                            view.setBackgroundColor(textSwatch.rgb)
                        })
                }
                return false
            }

        })
        .into(view)
}


@BindingAdapter("runtime")
fun setRuntime(view: TextView, runtime: Int) {
    val hr = runtime / 60
    val min = runtime % 60
    view.text = "( $hr hr $min min )"
}
