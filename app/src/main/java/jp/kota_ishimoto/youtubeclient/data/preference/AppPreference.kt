package jp.kota_ishimoto.youtubeclient.data.preference

import android.content.Context
import android.util.Log

internal class AppPreference(private val context: Context) : PreferenceInterface {

    override fun getString(key: String): String {
        val prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        return prefs.getString(key, "")
    }

    override fun saveString(key: String, value: String) {
        val prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
        Log.d(TAG, String.format("saved string [ %s ] to preference with key [ %s ].", value, key))
    }

    override fun delete(key: String) {
        val prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove(key)
        editor.apply()
        Log.d(TAG, String.format("deleted from preference with key [ %s ].", key))
    }

    companion object {

        private val TAG = AppPreference::class.java.simpleName

        private val PREF_FILE_NAME = "YoutubeClient"
    }

}
