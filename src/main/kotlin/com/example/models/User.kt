package com.example.models

import io.ktor.server.auth.Principal


data class User(
    val email :String,
    val password : String,
    val username : String
) :  Principal


