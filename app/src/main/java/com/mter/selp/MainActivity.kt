package com.mter.selp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mter.selp.databinding.ActivityMainBinding
import com.mter.selp.ui.fragments.MainFragment
import com.mter.selp.ui.fragments.StartLoginFragment


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

        val tv_dynamic = TextView(this)
        tv_dynamic.textSize = 20f
        tv_dynamic.text = "This is a dynamic TextView generated programmatically in Kotlin"

    }

}
