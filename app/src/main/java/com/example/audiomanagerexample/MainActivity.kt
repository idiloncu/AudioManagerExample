package com.example.audiomanagerexample

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.audiomanagerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var seekBar: SeekBar
    lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        seekBar = binding.seekBar
        seekBar.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        seekBar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        binding.increaseButton.setOnClickListener {
            increaseButton()
        }
        binding.decreaseButton.setOnClickListener {
            decreaseButton()
        }
        binding.seekBar.setOnClickListener{
            seekBar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        }
    }

    fun increaseButton() {
        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND)
        seekBar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        Toast.makeText(this, "Volume Up", Toast.LENGTH_SHORT).show()
    }

    fun decreaseButton() {
        audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND)
        seekBar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        Toast.makeText(this, "Volume Down", Toast.LENGTH_SHORT).show()
    }

}