package rest.api.resources

import rest.api.db.EventsDb
import rest.api.domain.events.EventResponse
import rest.api.helpers.verifyToken
import java.lang.Exception
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/events")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class EventResource(private val appSecret: String, private val dbHelper: EventsDb) {
    @GET
    fun getEvents(@HeaderParam("authorization") authToken: String?): EventResponse  {
        return try {
            verifyToken(authToken!!.split(" ")[1], appSecret)
            EventResponse("Fetch success", dbHelper.getEvents())
        } catch (ex: Exception) {
            EventResponse("Unauthorized")
        }
    }
}