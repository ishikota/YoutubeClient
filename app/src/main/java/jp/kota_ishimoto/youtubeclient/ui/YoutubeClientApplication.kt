package jp.kota_ishimoto.youtubeclient.ui

import android.app.Application

import jp.kota_ishimoto.youtubeclient.data.preference.PreferenceFactory
import jp.kota_ishimoto.youtubeclient.helper.account_helper.AccountHelperFactory

class YoutubeClientApplication : Application() {

    private var preferenceFactory: PreferenceFactory? = null

    private var accountHelperFactory: AccountHelperFactory? = null

    override fun onCreate() {
        super.onCreate()
    }

    fun getPreferenceFactory(): PreferenceFactory {
        if (preferenceFactory == null) {
            preferenceFactory = PreferenceFactory(this)
        }
        return preferenceFactory as PreferenceFactory
    }

    fun getAccountHelperFactory(): AccountHelperFactory {
        if (accountHelperFactory == null) {
            accountHelperFactory = AccountHelperFactory(this)
        }
        return accountHelperFactory as AccountHelperFactory
    }

}
