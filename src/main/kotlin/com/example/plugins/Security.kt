package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.authentification.JwtService
import com.example.db.DBImpl
import com.example.db.DatabaseConnection.database
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {

    val token = JwtService()
    val db = DBImpl()

    install(Authentication){
       jwt ("jwt"){
           realm = "Note Server"
           verifier(token.verifier)
           validate {
               val payload = it.payload
               val email = payload.getClaim("email").asString()
               val user = db.findUserByEmail(email)
               user
           }
       }
   }
}


