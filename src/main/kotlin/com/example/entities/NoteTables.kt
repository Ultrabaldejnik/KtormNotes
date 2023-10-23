package com.example.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.LocalDate

interface NoteTable : Entity<NoteTable>{

    val id : Int
    val email: UserTable
    val noteTitle : String
    val description : String
    val date_time : Long
}

object NoteTables : Table<NoteTable>("notes") {
    val id = int("id").primaryKey().bindTo { it.id }
    val email = varchar("email").references(UserTables){it.email}
    val noteTitle = varchar("noteTitle").bindTo { it.noteTitle }
    val description = varchar("description").bindTo { it.description }
    val date_time = long("date").bindTo { it.date_time }
}