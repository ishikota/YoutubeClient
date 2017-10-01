package jp.kota_ishimoto.youtubeclient.helper.account_helper

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.util.ExponentialBackOff
import jp.kota_ishimoto.youtubeclient.ui.YoutubeClientApplication
import java.util.*

class AccountHelperFactory(private val app: YoutubeClientApplication) {

    fun create(): AccountHelper {
        val preference = app.getPreferenceFactory().create()
        val credential = GoogleAccountCredential
                .usingOAuth2(app, Arrays.asList(*AppAccountHelper.SCOPES))
                .setBackOff(ExponentialBackOff())
        return AppAccountHelper(credential, preference)
    }
}
