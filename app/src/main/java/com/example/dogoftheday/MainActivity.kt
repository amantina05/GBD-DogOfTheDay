package com.example.dogoftheday

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dogoftheday.database.DogImageEntity
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    //making a variable that contains this functionality
    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as DogApplication).database.dogImageDao())    }

    //creates our main layout with button and image resource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeDogButton: Button = findViewById(R.id.button)
        val prevDogButton: Button = findViewById(R.id.prevButton)

        //setting the curr image to the image on the screen
        viewModel.currentlyDisplayedImage.observe(this,
            {
                val mainImage: ImageView = findViewById(R.id.imageView)
                //lib that helps you pass and retrieve and convert data
                Picasso.with(this).load(it.imgSrcUrl).into(mainImage)
            })


        changeDogButton.setOnClickListener {
            //getting the curr image displayed on the screen
            val currentImageUrl = viewModel.currentlyDisplayedImage.value?.imgSrcUrl
            //creating a dog entity out of it
            val newDogImage = currentImageUrl?.let { it1 -> DogImageEntity(imageUrl = it1) }

            //updating the new image on the screen
            viewModel.getNewDog()
            if (newDogImage != null) {
                //added that image to the db
                viewModel.addDog(newDogImage)
            }
            //deleted the dog image before this one
            viewModel.deleteMostRecentDog()
        }



        prevDogButton.setOnClickListener {
            //starts the second screen
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }
}