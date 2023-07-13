package com.mter.selp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.mter.selp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment, FragmentSign())
                .commit()
        }

        val tv_dynamic = TextView(this)
        tv_dynamic.textSize = 20f
        tv_dynamic.text = "This is a dynamic TextView generated programmatically in Kotlin"

    }

    fun createNewTextView(text: String): TextView? {
        val lparams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        val textView = TextView(this)
        textView.layoutParams = lparams
        textView.text = "New text: $text"
        return textView
    }
}
