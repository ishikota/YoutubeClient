package jp.kota_ishimoto.youtubeclient.helper.account_helper

import android.content.Intent
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.services.youtube.YouTubeScopes
import jp.kota_ishimoto.youtubeclient.data.preference.PreferenceInterface

internal class AppAccountHelper(_credential: GoogleAccountCredential, val preference: PreferenceInterface) : AccountHelper {

    override val credential: GoogleAccountCredential = _credential
        get() {
            if (!hasAccount()) {
                throw jp.kota_ishimoto.youtubeclient.data.exceptions.IllegalCredentialAccessException("Try to get credential without account settings")
            }
            val savedAccountName = preference.getString(jp.kota_ishimoto.youtubeclient.helper.account_helper.AppAccountHelper.Companion.PREF_KEY_ACCOUNT_NAME)
            field.selectedAccountName = savedAccountName
            return field
        }

    override fun hasAccount(): Boolean {
        val savedAccountName = preference.getString(PREF_KEY_ACCOUNT_NAME)
        return !savedAccountName.isEmpty()
    }

    override fun saveAccount(accountName: String) {
        preference.saveString(PREF_KEY_ACCOUNT_NAME, accountName)
        credential.selectedAccountName = accountName
    }

    override fun createChooseAccountIntent(): Intent = credential.newChooseAccountIntent()

    companion object {

        // AccountHelperFactory uses this scopes when creates credential for this class.
        val SCOPES = arrayOf(YouTubeScopes.YOUTUBE_READONLY)

        private val PREF_KEY_ACCOUNT_NAME = "PREF_KEY_ACCOUNT_NAME"
    }
}
