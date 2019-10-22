package rest.api

import io.dropwizard.Application
import io.dropwizard.setup.Environment
import rest.api.config.AuthConfig
import rest.api.resources.AuthResource
import rest.api.resources.EventResource

fun main(args: Array<String>) {
    AuthApp().run(*args)
}

class AuthApp: Application<AuthConfig>() {
    override fun run(configuration: AuthConfig, environment: Environment) {
        val authResource = AuthResource(configuration.appId, configuration.appSecret)
        environment.jersey().register(authResource)

        val eventResource = EventResource(configuration.appSecret)
        environment.jersey().register(eventResource)
    }
}

