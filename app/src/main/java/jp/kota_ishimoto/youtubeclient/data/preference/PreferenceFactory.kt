package jp.kota_ishimoto.youtubeclient.data.preference

import android.content.Context

class PreferenceFactory(private val context: Context) {

    fun create(): PreferenceInterface = AppPreference(context)

}
