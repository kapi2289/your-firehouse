package pl.kapiz.yourfirehouse.data.api.interceptor

import android.util.Base64
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.Response
import pl.kapiz.yourfirehouse.data.api.NoCredentialsGivenException
import pl.kapiz.yourfirehouse.data.repository.preferences.PreferencesRepository
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
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
        val time: Long = System.currentTimeMillis() / 1000

        val header = JsonObject().apply {
            addProperty("alg", "HS256")
            addProperty("typ", "JWT")
        }.toString().toBase64()

        val payload = JsonObject().apply {
            addProperty("iss", "https://terminal.eremiza.abakus.net.pl")
            addProperty("aud", "https://api.eremiza.abakus.net.pl")
            addProperty("sub", preferences.email ?: throw NoCredentialsGivenException())
            addProperty("exp", time + 180)
            addProperty("iat", time)
            addProperty("nbf", time - 60)
        }.toString().toBase64()

        val signature = Mac.getInstance("HmacSHA256").apply {
            init(
                SecretKeySpec(
                    preferences.secret?.toByteArray() ?: throw NoCredentialsGivenException(),
                    algorithm
                )
            )
            update("$header.$payload".toByteArray())
        }.doFinal().toBase64()

        return "$header.$payload.$signature"
    }

    private fun ByteArray.toBase64(): String =
        Base64.encodeToString(this, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)

    private fun String.toBase64(): String = toByteArray().toBase64()
}
