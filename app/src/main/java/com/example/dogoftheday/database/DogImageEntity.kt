package com.example.dogoftheday.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


////      goes off annotation, annotate the class that we want
//@Entity(tableName = "DogImages")
////      define the diff column we have/ want in our table, & auto generate an id
//
//
//data class DogImageEntity(
//    @Json(name = "message")
//    @ColumnInfo(name = "image_url")
//    val imageUrl: String,
//
//    )
//

@Entity(tableName = "DogImages")data class DogImageEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")   val id: Int = 0,
    @ColumnInfo(name = "image_url")
    val imageUrl: String) {}