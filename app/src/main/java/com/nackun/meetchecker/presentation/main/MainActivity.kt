package com.nackun.meetchecker.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nackun.meetchecker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainPager.adapter = MainViewPagerAdapter(this)
    }
}