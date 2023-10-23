package com.example.models

@kotlinx.serialization.Serializable
data class Note(
    val id : String,
    val noteTitle : String,
    val description : String,
    val date : Long
)
