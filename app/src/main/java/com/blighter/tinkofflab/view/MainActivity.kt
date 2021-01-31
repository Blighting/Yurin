package com.blighter.tinkofflab.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blighter.tinkofflab.R
import com.blighter.tinkofflab.databinding.ActivityMainBinding
import com.blighter.tinkofflab.viewModel.RandomGifViewModel
class MainActivity : AppCompatActivity() {
    val viewModel: RandomGifViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }


}