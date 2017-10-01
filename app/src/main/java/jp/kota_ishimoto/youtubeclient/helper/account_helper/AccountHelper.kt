package jp.kota_ishimoto.youtubeclient.helper.account_helper

import android.content.Intent

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential

interface AccountHelper {

    val credential: GoogleAccountCredential

    fun hasAccount(): Boolean

    fun saveAccount(accountName: String)

    fun createChooseAccountIntent(): Intent

}
