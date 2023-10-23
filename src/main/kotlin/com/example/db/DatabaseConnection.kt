package com.example.db

import org.ktorm.database.Database

object DatabaseConnection {


    val database = Database.connect(
        url = "jdbc:postgresql://localhost:5432/notes",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "admin"
    )
}