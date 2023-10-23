package com.example.routes

import com.example.db.DBImpl
import com.example.models.Note
import com.example.models.SimpleResponse
import com.example.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.NoteRoutes(db: DBImpl, hashFunction: (String) -> String) {
    authenticate("jwt") {
        post("/v1/notes/create") {
            val note = try {
                call.receive<Note>()
            } catch (e: Exception) {
                call.respond(HttpStatusCode.OK, SimpleResponse(false, "Missing Fields"))
                return@post
            }

            try {
                val email = call.principal<User>()!!.email
                db.addNote(note, email)
                call.respond(HttpStatusCode.OK, SimpleResponse(true, "Note added successfully!"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, SimpleResponse(false, e.message ?: " Some problems..."))
            }
        }

        get("/v1/notes") {
            try {
                val email = call.principal<User>()!!.email
                val notes = db.getAllNotes(email)
                call.respond(HttpStatusCode.OK, notes)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, emptyList<Note>())
            }
        }

        post("v1/notes/update") {
            val note = try {
                call.receive<Note>()
            } catch (e: Exception) {
                call.respond(HttpStatusCode.OK, SimpleResponse(false, "Missing Fields"))
                return@post
            }

            try {
                val email = call.principal<User>()!!.email
                db.updateNotes(note, email)
                call.respond(HttpStatusCode.OK, SimpleResponse(true, "Note updated successfully!"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, SimpleResponse(false, e.message ?: " Some problems..."))
            }
        }

        delete("/v1/notes/delete") {
            val noteId = try {
                call.request.queryParameters["id"]!!
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "QueryParameter:id os not prepared"))
                return@delete
            }

            try {
                val email = call.principal<User>()!!.email
                db.deleteNotes(noteId.toInt(), email)
                call.respond(HttpStatusCode.OK, SimpleResponse(true,"Note deleted successfully"))
            }catch (e : Exception){
                call.respond(HttpStatusCode.Conflict, SimpleResponse(false, e.message ?: " Some problems..."))
            }
        }

    }
}