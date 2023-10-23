package com.example.db

import com.example.models.User
import com.example.db.DatabaseConnection.database
import com.example.entities.NoteTables
import com.example.entities.UserTables
import com.example.models.Note
import org.ktorm.dsl.*
import java.time.LocalDate

class DBImpl {

    fun getUsers(): List<User> {
        return database.from(UserTables).select().map {
            val username = it[UserTables.username]!!
            val password = it[UserTables.password]!!
            val email = it[UserTables.email]!!
            User(username = username, password = password, email = email)
        }
    }

    fun addUser(user: User) {
        database.insert(UserTables) {
            set(it.email, user.email)
            set(it.password, user.password)
            set(it.username, user.username)
        }
    }

    fun findUserByEmail(email: String): User? {
        return database.from(UserTables).select().where(UserTables.email eq email).map {
            val username = it[UserTables.username]!!
            val password = it[UserTables.password]!!
            val email = it[UserTables.email]!!
            User(username = username, password = password, email = email)
        }.firstOrNull()
    }

    fun addNote(note:Note,email: String){
        database.insert(NoteTables){
            set(it.id,note.id)
            set(it.email, email)
            set(it.noteTitle, note.noteTitle)
            set(it.description, note.noteTitle)
            set(it.date,note.date)
        }
    }

    fun getAllNotes(email: String): List<Note>{
        return database.from(NoteTables).select().where(UserTables.email eq email).map{
            val id = it[NoteTables.id]!!
            val noteTitle=it[NoteTables.noteTitle]!!
            val description= it[NoteTables.description]!!
            val date=it[NoteTables.date]!!
            Note(id=id, noteTitle = noteTitle, description = description, date = date)
        }
    }

    fun updateNotes(note: Note, email: String){
        database.update(NoteTables){
            set(it.noteTitle, note.noteTitle)
            set(it.description, note.noteTitle)
            set(it.date,note.date)
            where {
                it.email eq email
            }
        }
    }

    fun deleteNotes(id : String, email: String){
        database.delete(NoteTables){
            it.id eq id
            it.email eq email
        }
    }
}