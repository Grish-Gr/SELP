package com.mter.selp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mter.selp.databinding.ActivityMainBinding
import com.mter.selp.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment, MainFragment())
                .commit()
        }
    }
}