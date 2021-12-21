package com.example.dogoftheday.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


//  Dao is used to access the db, query center accessing the db
@Dao

interface DogImageDao {
    //gets all
    @Query("SELECT * FROM DogImages")
    fun getAllDogImages(): Flow<List<DogImageEntity>>

    //returns all the rows in desc order and selects the first row
    @Query("SELECT * FROM DogImages ORDER BY id DESC LIMIT 1")
    fun getMostRecentlyAddDog(): DogImageEntity

    //sub query, selecting max id and sub 1, inorder to get the prev one
    @Query("DELETE from DogImages where id=(select max(id)-1 from DogImages)")
    suspend fun deleteDog(): Unit

    //gets one id higher than the prev one
    @Insert
    suspend fun addDogImage (dogImageEntity: DogImageEntity)

}