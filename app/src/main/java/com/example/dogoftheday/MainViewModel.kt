package com.example.dogoftheday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogoftheday.network.DogImage
import com.example.dogoftheday.network.DogImageApi
import kotlinx.coroutines.launch


//main view model inherits from view model which is an android lifecycle
class MainViewModel : ViewModel() {

    //mutable & live so it can constantly change and update throughout the app
    private val _currentlyDisplayedImage = MutableLiveData<DogImage>()
    //live but not as mutable as the one above
    val currentlyDisplayedImage: LiveData<DogImage> = _currentlyDisplayedImage

    //automatically initialize so we get data as quick as possible
    init {
        getNewDog()
    }

    //return a random dog image --using a coroutine
    fun getNewDog () {
        viewModelScope.launch {
            //gets the first item in list from the response
            _currentlyDisplayedImage.value = DogImageApi.retrofitService.getRandomDogImage()
        }

    }


}