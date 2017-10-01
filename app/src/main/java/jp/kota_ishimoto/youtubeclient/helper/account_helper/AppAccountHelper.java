package jp.kota_ishimoto.youtubeclient.helper.account_helper;

import android.content.Intent;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.services.youtube.YouTubeScopes;

import jp.kota_ishimoto.youtubeclient.data.exceptions.IllegalCredentialAccessException;
import jp.kota_ishimoto.youtubeclient.data.preference.PreferenceInterface;

class AppAccountHelper implements AccountHelper {

    // AccountHelperFactory uses this scopes when creates credential for this class.
    static final String[] SCOPES = { YouTubeScopes.YOUTUBE_READONLY };

    private static final String PREF_KEY_ACCOUNT_NAME = "PREF_KEY_ACCOUNT_NAME";

    private final GoogleAccountCredential credential;

    private final PreferenceInterface preference;

    AppAccountHelper(GoogleAccountCredential credential, PreferenceInterface preference) {
        this.credential = credential;
        this.preference = preference;
    }

    @Override
    public GoogleAccountCredential getCredential() {
        if (!hasAccount()) {
            throw new IllegalCredentialAccessException("Try to get credential without account settings");
        }
        String savedAccountName = preference.getString(PREF_KEY_ACCOUNT_NAME);
        credential.setSelectedAccountName(savedAccountName);
        return credential;
    }

    @Override
    public boolean hasAccount() {
        String savedAccountName = preference.getString(PREF_KEY_ACCOUNT_NAME);
        return !savedAccountName.isEmpty();
    }

    @Override
    public void saveAccount(String accountName) {
        preference.saveString(PREF_KEY_ACCOUNT_NAME, accountName);
        credential.setSelectedAccountName(accountName);
    }

    @Override
    public Intent createChooseAccountIntent() {
        return credential.newChooseAccountIntent();
    }
}
