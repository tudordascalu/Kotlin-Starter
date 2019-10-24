package rest.api.helpers

import org.junit.Assert.assertFalse
import org.junit.Test
import rest.api.domain.auth.User

class AuthHelperTest {
    @Test
    fun generateJwtTokenTest() {
        val token = generateJwtToken(User("tudor", "pass"),"test", "testSecret")
        assertFalse(token.isEmpty())
    }

    @Test
    fun verifyTokenTest() {
        val token = generateJwtToken(User("tudor", "pass"),"test", "testSecret")
        assertFalse(verifyToken(token, "testSecret") == null)
    }
}