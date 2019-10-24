package rest.api.resources

import org.junit.Assert.assertEquals
import org.junit.Test

class EventResourceTest {
    @Test
    fun getEvents() {
        assertEquals(
            "Unauthorized",
            EventResource("mockSecret")
                .getEvents("mockToken")
                .message
        )
    }
}