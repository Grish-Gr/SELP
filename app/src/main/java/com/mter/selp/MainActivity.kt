package com.mter.selp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mter.selp.databinding.ActivityMainBinding
import com.mter.selp.ui.fragments.MainFragment
import com.mter.selp.ui.fragments.StartLoginFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // В url заменить на свой ip
    val url = "http://192.168.1.102:8080/api/v1/admin/users/export/excel"
    val i = Intent(Intent.ACTION_VIEW)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment, StartLoginFragment())
                .commit()
        }

        val tv_dynamic = TextView(this)
        tv_dynamic.textSize = 20f
        tv_dynamic.text = "This is a dynamic TextView generated programmatically in Kotlin"

    }

    fun downloadStat(view: View){
        i.data = Uri.parse(url)
        startActivity(i)
    }

}
