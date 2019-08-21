package com.patrickchow.androidanimatedimages

import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val drawableIds = listOf(R.drawable.giphy,
                             R.drawable.puppy_animated)
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // animateGif(R.drawable.giphy, animated_image)
        animateAnimationDrawable(R.drawable.puppy_animated, animated_image)
    }

    //Animates the gif
    private fun animateGif(image: Int, view: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val gifDrawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(resources, image))
            view.setImageDrawable(gifDrawable)
            (gifDrawable as AnimatedImageDrawable).start()
        }
    }

    //Animates the drawable png frames
    private fun animateAnimationDrawable(id: Int, view: ImageView) {
        val frameDrawable = ContextCompat.getDrawable(this, id)
        view.setImageDrawable(frameDrawable)
        (frameDrawable as AnimationDrawable).start()
    }

}
