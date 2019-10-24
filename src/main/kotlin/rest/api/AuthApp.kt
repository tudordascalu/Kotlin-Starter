package rest.api

import io.dropwizard.Application
import io.dropwizard.setup.Environment
import rest.api.config.AuthConfig
import rest.api.db.EventsDb
import rest.api.db.UsersDb
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
            val eventsDb = EventsDb(connection)
            val usersDb = UsersDb(connection)

            println("JAVA JDBC PostgreSQL Example")

            val authResource = AuthResource(configuration.appId, configuration.appSecret, usersDb)
            environment.jersey().register(authResource)

            val eventResource = EventResource(configuration.appSecret, eventsDb)
            environment.jersey().register(eventResource)
        } catch(e: SQLException) {
            println("Connection Failed")
            e.printStackTrace()
        }
    }
}
