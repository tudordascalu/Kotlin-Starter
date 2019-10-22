package rest.api.resources

import rest.api.domain.auth.AuthRequest
import rest.api.domain.auth.AuthResponse
import rest.api.domain.auth.User
import rest.api.helpers.generateJwtToken
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/auth")
@Produces(APPLICATION_JSON)
class AuthResource(private val appId: String, private val appSecret: String) {
    @Path("/login")
    @POST
    @Consumes(APPLICATION_JSON)
    fun login(authRequest: AuthRequest): AuthResponse {
        val user = User(authRequest.username, authRequest.pass);
        val token = generateJwtToken(user, appId, appSecret)
        return AuthResponse("Login success", token)
    }
}