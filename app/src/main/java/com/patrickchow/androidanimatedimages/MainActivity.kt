package com.patrickchow.androidanimatedimages

import android.graphics.ImageDecoder
import android.graphics.drawable.Animatable
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

    var currentlyPlaying : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Default play the hedgehog gif at start up
        animateGif(drawableIds[index], animated_image)

        iv_up.setOnClickListener{
            increaseIndex()
            animated_image.setImageDrawable(ContextCompat.getDrawable(this, drawableIds[index]))
        }

        iv_down.setOnClickListener {
            decreaseIndex()
            animated_image.setImageDrawable(ContextCompat.getDrawable(this, drawableIds[index]))

        }

        iv_change.setOnClickListener {

            //If we're playing, then play the animation
            if(currentlyPlaying) {
                currentlyPlaying = false
                //Change the image of the play button
                animateVectorDrawable(R.drawable.avd_playtopause, it as ImageView)
                //Depending on which index were're currently on, play that index from the drawableIds
                when(index){
                    0 -> animateGif(drawableIds[index], animated_image)
                    1 -> animateAnimationDrawable(drawableIds[index], animated_image)
                }
            }
            else{
                //If we're not playing, then show the still image
                currentlyPlaying = true
                //Change the image of the play button
                animateVectorDrawable(R.drawable.avd_pausetoplay, it as ImageView)
                when(index){
                    0 -> animated_image.setImageDrawable(ContextCompat.getDrawable(this, drawableIds[index]))
                    1 -> animated_image.setImageDrawable(ContextCompat.getDrawable(this, drawableIds[index]))
                }
            }
        }
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

    private fun animateVectorDrawable(id: Int, view: ImageView){
        val animatedVectorDrawable = ContextCompat.getDrawable(this, id)
        view.setImageDrawable(animatedVectorDrawable)
        (animatedVectorDrawable as Animatable).start()
    }

    fun increaseIndex(){
        index++
        if(index >= drawableIds.size){
            index = 0
        }
    }

    fun decreaseIndex(){
        index--
        if(index < 0){
            index = drawableIds.size-1
        }
    }

}
