package pl.kapiz.yourfirehouse.data.api.interceptor

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import okhttp3.Interceptor
import okhttp3.Response
import pl.kapiz.yourfirehouse.data.api.NoCredentialsGivenException
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

        return if (preferences.secret != null) {
            val jwt = generateJWT()

            chain.proceed(
                request.newBuilder()
                    .addHeader("JWT", jwt)
                    .build()
            )
        } else {
            chain.proceed(request)
        }
    }

    private fun generateJWT(): String {
        val time: Long = System.currentTimeMillis()

        val alg = Algorithm.HMAC256(preferences.secret ?: throw NoCredentialsGivenException())

        return JWT.create()
            .withIssuer("https://terminal.eremiza.abakus.net.pl")
            .withAudience("https://api.eremiza.abakus.net.pl")
            .withSubject(preferences.email ?: throw NoCredentialsGivenException())
            .withExpiresAt(Date(time + 180000))
            .withIssuedAt(Date(time))
            .withNotBefore(Date(time - 60000))
            .sign(alg)
    }
}
