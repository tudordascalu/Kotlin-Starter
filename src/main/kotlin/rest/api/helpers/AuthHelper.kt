package rest.api.helpers

import io.jsonwebtoken.*
import rest.api.domain.auth.User
import java.util.*
import kotlin.collections.HashMap

fun verifyToken(token: String, appSecret: String): Jws<Claims>? {
    return Jwts.parser()
        .setSigningKey(appSecret)
        .parseClaimsJws(token)
}

fun generateJwtToken(user: User, appId: String, appSecret: String): String {
    val algorithm = SignatureAlgorithm.HS256
    val claims: HashMap<String, Any?> = HashMap<String, Any?>();

    claims["username"] = user.username
    claims["sub"] =  "AuthRequest"

    return Jwts.builder()
        .setIssuer(appId)
        .setClaims(claims)
        .signWith(algorithm, appSecret)
        .setExpiration(Date(2019, 1, 1))
        .compact()
}