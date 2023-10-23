package com.example.models

@kotlinx.serialization.Serializable
data class RegisterRequest(
    val email : String,
    val name : String,
    val password : String,
)
