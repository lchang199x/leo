package com.tptz.leo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.tptz.leo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loading.setOnClickListener {
            handleLoading(binding.viewGroup)
        }
        binding.empty.setOnClickListener {
            handleEmpty(binding.viewGroup)
        }
        binding.offline.setOnClickListener {
            handleOffline(binding.viewGroup)
        }
    }

    override fun onResume() {
        super.onResume()
        ViewCompat.getWindowInsetsController(binding.root)?.let {
            it.hide(WindowInsetsCompat.Type.navigationBars())
            it.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}