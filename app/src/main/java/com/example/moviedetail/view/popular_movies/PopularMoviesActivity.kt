package com.example.moviedetail.view.popular_movies

import com.example.moviedetail.R
import com.example.moviedetail.base.BaseActivity
import com.example.moviedetail.databinding.ActivityMainBinding

class PopularMoviesActivity : BaseActivity<ActivityMainBinding>() {

    override val layout: Int
        get() = R.layout.activity_main

    override fun init(bind: ActivityMainBinding) {

    }

    override var title: String = "Popular Movies"
}