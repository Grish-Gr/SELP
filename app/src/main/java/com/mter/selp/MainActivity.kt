package com.mter.selp

import android.content.Context
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mter.selp.databinding.ActivityMainBinding
import com.mter.selp.ui.fragments.MainFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createSimpleDialog()
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (supportFragmentManager.fragments.isEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment, MainFragment())
                .commit()
        }

    }

    private var mediaPlayer: MediaPlayer? = null
    var count = 1
    var secCount = 1


    fun playAndPauseSound(view: View){
        if (count == 0){
            pauseSound(view)
        }
        else{
            playSound(view)
        }
    }

    private fun playSound(view: View) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.relax)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
        } else mediaPlayer!!.start()
        count = 0
    }

    fun pauseSound(view: View) {
        mediaPlayer?.pause()
        count = 1
    }

    private fun createSimpleDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Дружеское напоминание")
        builder.setMessage("Привет, не забудь пожалуйста выбрать какое у тебя сейчас настроение, это достаточно важно, спасибо.")
        builder.setNeutralButton("Хорошо"){ dialog, which ->

        }
        builder.setNegativeButton("Позже"){dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}
