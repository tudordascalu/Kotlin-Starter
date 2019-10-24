package rest.api.resources

import rest.api.db.UsersDb
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
@Consumes(APPLICATION_JSON)
class AuthResource(private val appId: String, private val appSecret: String, val usersDb: UsersDb) {
    @Path("/login")
    @POST
    fun login(authRequest: AuthRequest): AuthResponse {

        // here validation of user data against DB should happen

        val user = User(authRequest.username, authRequest.pass);
        val token = generateJwtToken(user, appId, appSecret)
        return AuthResponse("Login success", token)
    }

    @Path("/signup")
    @POST
    fun signup(authRequest: AuthRequest): AuthResponse {
        usersDb.createUser(authRequest.username, authRequest.pass)
        return AuthResponse("Signup success")
    }
}