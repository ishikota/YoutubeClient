package jp.kota_ishimoto.youtubeclient.ui;

import android.app.Application;

import jp.kota_ishimoto.youtubeclient.data.preference.PreferenceFactory;
import jp.kota_ishimoto.youtubeclient.helper.account_helper.AccountHelperFactory;

public class YoutubeClientApplication extends Application {

    private PreferenceFactory preferenceFactory;

    private AccountHelperFactory accountHelperFactory;

    public void onCreate() {
        super.onCreate();
    }

    public PreferenceFactory getPreferenceFactory() {
        if (preferenceFactory == null) {
            preferenceFactory = new PreferenceFactory(this);
        }
        return preferenceFactory;
    }

    public AccountHelperFactory getAccountHelperFactory() {
        if (accountHelperFactory == null) {
            accountHelperFactory = new AccountHelperFactory(this);
        }
        return accountHelperFactory;
    }

}
