package com.example.dogoftheday

import androidx.lifecycle.*
import com.example.dogoftheday.database.DogImageDao
import com.example.dogoftheday.database.DogImageEntity
import com.example.dogoftheday.network.DogImage
import com.example.dogoftheday.network.DogImageApi
import kotlinx.coroutines.launch


//main view model inherits from view model which is an android lifecycle
class MainViewModel (private val dogImageDao: DogImageDao) : ViewModel() {

    //mutable & live so it can constantly change and update throughout the app
    private val _currentlyDisplayedImage = MutableLiveData<DogImage>()

    //live but not as mutable as the one above
    val currentlyDisplayedImage: LiveData<DogImage> = _currentlyDisplayedImage

    //automatically initialize so we get data as quick as possible
    init {
        getNewDog()
    }

    //return a random dog image --using a coroutine (used to split cp brain/ multitasking)
    fun getNewDog () {
        viewModelScope.launch {
            //gets the first item in list from the response
            _currentlyDisplayedImage.value = DogImageApi.retrofitService.getRandomDogImage()
        }
    }

    //takes a dog image entity & whats passed into this function is what is stored in db --using a coroutine
    fun addDog (dogImageEntity: DogImageEntity) {
        viewModelScope.launch {
            dogImageDao.addDogImage(dogImageEntity)
        }
    }

    //deletes the most recent dog using query from before
    fun deleteMostRecentDog() {
        viewModelScope.launch {
            dogImageDao.deleteDog()
        }
    }

    //gets all of the dogs images, returns a flow list of dogs entity and then turned into live data
    fun getAllDogs(): LiveData<List<DogImageEntity>> {
        return dogImageDao.getAllDogImages().asLiveData()
    }

}


//when you take a dog image dao, suppresses an error within the program
class MainViewModelFactory(
    private val dogImageDao: DogImageDao) : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel (dogImageDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

