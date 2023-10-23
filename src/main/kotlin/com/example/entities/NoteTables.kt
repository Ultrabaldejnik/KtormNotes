package com.example.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.LocalDate

interface NoteTable : Entity<NoteTable>{

    val id : String
    val email: UserTable
    val noteTitle : String
    val description : String
    val date : Long
}

object NoteTables : Table<NoteTable>("notes") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val email = varchar("id").references(UserTables){it.email}
    val noteTitle = varchar("noteTitle").bindTo { it.noteTitle }
    val description = varchar("description").bindTo { it.description }
    val date = long("date").bindTo { it.date }
}