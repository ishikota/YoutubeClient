package jp.kota_ishimoto.youtubeclient.data.preference

interface PreferenceInterface {

    fun getString(key: String): String

    fun saveString(key: String, value: String)

    fun delete(key: String)

}
