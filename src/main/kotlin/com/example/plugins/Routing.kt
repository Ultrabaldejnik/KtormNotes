package com.example.plugins

import com.example.authentification.JwtService
import com.example.authentification.hash
import com.example.db.DBImpl
import com.example.routes.NoteRoutes
import com.example.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val db = DBImpl()
    val jwt = JwtService()
    val hashFunction = {s : String -> hash(s) }


    routing {
        userRoutes(db,jwt,hashFunction)
        NoteRoutes(db,hashFunction)
    }
}
