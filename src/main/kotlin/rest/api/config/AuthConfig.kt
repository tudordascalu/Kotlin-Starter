package rest.api.config

import io.dropwizard.Configuration

data class AuthConfig(val appId: String = "undefined", val appSecret: String = "undefined"): Configuration()