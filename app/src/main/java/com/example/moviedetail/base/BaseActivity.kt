package com.example.moviedetail.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : ViewDataBinding?> : DaggerAppCompatActivity() {
    abstract val layout: Int @LayoutRes get
    abstract var title: String
    abstract fun init(bind: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<T>(this, layout)
        init(binding!!)
        supportActionBar?.title = title
    }
}