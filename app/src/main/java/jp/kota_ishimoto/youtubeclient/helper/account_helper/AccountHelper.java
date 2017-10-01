package jp.kota_ishimoto.youtubeclient.helper.account_helper;

import android.content.Intent;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

public interface AccountHelper {

    GoogleAccountCredential getCredential();

    boolean hasAccount();

    void saveAccount(String accountName);

    Intent createChooseAccountIntent();

}
