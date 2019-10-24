package rest.api.resources

import org.junit.Assert.assertEquals
import org.junit.Test
import rest.api.domain.auth.AuthRequest

class AuthResourceTest {
    @Test
    fun login() {
        assertEquals(
            "Login success",
            AuthResource("test", "test")
                .login(AuthRequest("tudor", "p")).message
        )
    }
}