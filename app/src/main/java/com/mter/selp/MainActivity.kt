package com.mter.selp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
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
    var count = true


    fun playAndPauseSound(view: View){
        if (!count){
            pauseSound(view)
        }
        else{
            playSound(view)
        }
    }

    private fun playSound(view: View) {
        view.foreground = AppCompatResources.getDrawable(view.context, R.drawable.ic_volume_off)
        mediaPlayer = MediaPlayer.create(this, R.raw.relax)
        mediaPlayer!!.isLooping = true
        mediaPlayer!!.start()
        count = false
    }

    private fun pauseSound(view: View) {
        view.foreground = AppCompatResources.getDrawable(view.context, R.drawable.ic_volume_on)
        mediaPlayer?.pause()
        count = true
    }



    private fun createSimpleDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Дружеское напоминание")
        builder.setMessage("Привет, не забудь пожалуйста выбрать какое у тебя сейчас настроение, это достаточно важно, спасибо.")
        builder.setNeutralButton("Хорошо"){ dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

}
