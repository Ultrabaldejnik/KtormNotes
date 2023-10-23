package com.example.models

@kotlinx.serialization.Serializable
data class SimpleResponse(
    val success : Boolean,
    val message : String
)
