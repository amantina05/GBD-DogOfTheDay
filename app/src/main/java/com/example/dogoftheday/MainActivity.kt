package com.example.dogoftheday

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    //making a variable that contains this functionality
    private val viewModel: MainViewModel by viewModels()

    //creates our main layout with button and image resource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeDogButton: Button = findViewById(R.id.button)

        viewModel.currentlyDisplayedImage.observe(this,
            {
                val mainImage: ImageView = findViewById(R.id.imageView)
                //lib that helps you pass and retrieve and convert data
                Picasso.with(this).load(it.imgSrcUrl).into(mainImage)
            })

        changeDogButton.setOnClickListener{
            viewModel.getNewDog()
        }
    }
}