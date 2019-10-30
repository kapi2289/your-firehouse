package pl.kapiz.yourfirehouse.data.api.interceptor

import android.util.Base64
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import okhttp3.Interceptor
import okhttp3.Response
import pl.kapiz.yourfirehouse.data.repository.preferences.PreferencesRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JWTInterceptor @Inject constructor(
    private val preferences: PreferencesRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val jwt = generateJWT()

        return chain.proceed(
            request.newBuilder()
                .addHeader("JWT", jwt)
                .build()
        )
    }

    private fun generateJWT(): String {
        val time: Long = System.currentTimeMillis()

        return Jwts.builder()
            .setIssuer("https://terminal.eremiza.abakus.net.pl")
            .setAudience("https://api.eremiza.abakus.net.pl")
            .setSubject(preferences.email)
            .setExpiration(Date(time + 180000))
            .setIssuedAt(Date(time))
            .setNotBefore(Date(time - 60000))
            .signWith(
                Keys.hmacShaKeyFor(
                    Base64.encode(preferences.passwordHash?.toByteArray(), Base64.DEFAULT)
                ), SignatureAlgorithm.HS256
            )
            .compact()
    }
}
