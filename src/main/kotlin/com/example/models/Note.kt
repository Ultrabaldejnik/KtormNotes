package com.example.models

@kotlinx.serialization.Serializable
data class Note(
    val id : Int,
    val noteTitle : String,
    val description : String,
    val date_time : Long
)
