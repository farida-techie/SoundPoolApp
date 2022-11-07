package com.malkinfo.soundpoolapp

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    var soundPool: SoundPool? = null
    var game_over = 0
    var level_complete = 0
    var player_died = 0
    /**ok now run it*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soundPool = if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.LOLLIPOP
        ) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(
                    AudioAttributes.USAGE_ASSISTANCE_SONIFICATION
                )
                .setContentType(
                    AudioAttributes.CONTENT_TYPE_SONIFICATION
                )
                .build()
            SoundPool.Builder()
                .setMaxStreams(3)
                .setAudioAttributes(
                    audioAttributes
                )
                .build()
        } else {
            SoundPool(
                3,AudioManager.STREAM_MUSIC,0)
        }
        game_over = soundPool!!.load(this,
            R.raw.game_over,
            1
        )

        level_complete = soundPool!!.load(this,
            R.raw.level_complete,
            1
        )
        player_died = soundPool!!.load(this,
            R.raw.player_died,
            1
        )
    }

    fun playSound(v:View){
        when(v.id){
            R.id.button_sound1->{
                soundPool!!.play(game_over,
                    1f,1f,0,0,1f)
                soundPool!!.autoPause()

            }
            R.id.button_sound2->{
                soundPool!!.play(player_died,
                    1f,1f,0,0,1f)
            }
            R.id.button_sound3->{
                soundPool!!.play(level_complete,
                    1f,1f,0,0,1f)

            }
        }
    }
}