package jp.kota_ishimoto.youtubeclient.helper.account_helper;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;

import java.util.Arrays;

import jp.kota_ishimoto.youtubeclient.data.preference.PreferenceInterface;
import jp.kota_ishimoto.youtubeclient.ui.YoutubeClientApplication;

import static jp.kota_ishimoto.youtubeclient.helper.account_helper.AppAccountHelper.SCOPES;

public class AccountHelperFactory {

    private YoutubeClientApplication app;

    public AccountHelperFactory(YoutubeClientApplication app) {
        this.app = app;
    }

    public AccountHelper create() {
        PreferenceInterface preference = app.getPreferenceFactory().create();
        GoogleAccountCredential credential = GoogleAccountCredential
                .usingOAuth2(app, Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());
        return new AppAccountHelper(credential, preference);
    }
}
