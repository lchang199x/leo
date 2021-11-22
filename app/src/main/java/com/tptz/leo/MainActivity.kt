package com.tptz.leo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tptz.leo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loading.setOnClickListener {
            handleLoading(binding.rootView)
        }
        binding.empty.setOnClickListener {
            handleEmpty(binding.rootView)
        }
        binding.offline.setOnClickListener {
            handleOffline(binding.rootView)
        }
    }
}