package com.example.entities

import com.example.entities.UserTables.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table

import org.ktorm.schema.varchar

interface UserTable : Entity<UserTable>{
    val email : String
    val username : String
    val password : String
}

object UserTables : Table<UserTable>("users") {

    val email = varchar("email").bindTo { it.email }.primaryKey()
    val username = varchar("username").bindTo { it.username }
    val password = varchar("password").bindTo { it.password }
}