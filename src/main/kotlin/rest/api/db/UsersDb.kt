package rest.api.db

import rest.api.domain.auth.User
import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement

class UsersDb (private val connection: Connection) {
    fun getUsers(): List<User> {
        val statement: Statement = connection.createStatement()
        val userList = mutableListOf<User>()
        val resultSet = statement.executeQuery("SELECT * FROM users")

        while(resultSet.next()) {
            val username = resultSet.getString("username")
            val pass = resultSet.getString("pass")
            userList.add(User(username, pass))
        }

        return userList
    }

    fun createUser(username: String, pass: String) {
        try {
            val statement: Statement = connection.createStatement()
            statement.executeUpdate("INSERT INTO users (username, pass) VALUES ('$username', '$pass')")

        } catch(e: SQLException) {
            println("Ther was an issue when inserting the user")
            e.printStackTrace()
        }
    }
}