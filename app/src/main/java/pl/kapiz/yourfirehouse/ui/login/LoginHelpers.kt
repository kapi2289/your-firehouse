package pl.kapiz.yourfirehouse.ui.login

import android.util.Base64
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

fun String.toSecret(): String =
    MessageDigest.getInstance("SHA-1").run {
        reset()
        update(toByteArray(UTF_8))
        Base64.encodeToString(digest(), Base64.NO_WRAP)
    }
