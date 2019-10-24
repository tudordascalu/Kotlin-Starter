package rest.api

import io.dropwizard.Application
import io.dropwizard.setup.Environment
import rest.api.config.AuthConfig
import rest.api.db.DbHelper
import rest.api.resources.AuthResource
import rest.api.resources.EventResource
import java.sql.*

fun main(args: Array<String>) {
    AuthApp().run(*args)
}

class AuthApp: Application<AuthConfig>() {
    override fun run(configuration: AuthConfig, environment: Environment) {
        try{
            val connection: Connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/events", "postgres", "")
            val dbHelper = DbHelper(connection)

            println("JAVA JDBC PostgreSQL Example")

            val authResource = AuthResource(configuration.appId, configuration.appSecret)
            environment.jersey().register(authResource)

            val eventResource = EventResource(configuration.appSecret, dbHelper)
            environment.jersey().register(eventResource)
        } catch(e: SQLException) {
            println("Connection Failed")
            e.printStackTrace()
        }
    }
}
