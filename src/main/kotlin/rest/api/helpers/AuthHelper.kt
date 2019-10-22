package rest.api.helpers

import io.jsonwebtoken.*
import org.apache.commons.lang3.time.DateUtils
import rest.api.domain.auth.User
import java.util.*
import kotlin.collections.HashMap

fun verifyToken(token: String, appSecret: String): Jws<Claims>? {
    println(Jwts.parser()
        .setSigningKey(appSecret)
        .parseClaimsJws(token))
    return Jwts.parser()
        .setSigningKey(appSecret)
        .parseClaimsJws(token)
}

fun generateJwtToken(user: User, appId: String, appSecret: String): String {
    val algorithm = SignatureAlgorithm.HS256
    val expirationTime: Date = DateUtils.addMinutes(Date(), 10)
    val claims: HashMap<String, Any?> = HashMap<String, Any?>();
    claims["username"] = user.username
    claims["sub"] =  "AuthRequest"

    return Jwts.builder()
        .setIssuer(appId)
        .setClaims(claims)
        .signWith(algorithm, appSecret)
        .setExpiration(expirationTime)
        .compact()
}