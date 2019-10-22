package rest.api.domain.auth

import com.fasterxml.jackson.annotation.JsonProperty

class AuthRequest(@JsonProperty("u") val username: String, @JsonProperty("p") val pass: String)