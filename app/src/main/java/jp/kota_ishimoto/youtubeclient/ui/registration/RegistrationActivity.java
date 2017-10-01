package jp.kota_ishimoto.youtubeclient.ui.registration;

import android.Manifest;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import jp.kota_ishimoto.youtubeclient.R;
import jp.kota_ishimoto.youtubeclient.ui.YoutubeClientApplication;
import jp.kota_ishimoto.youtubeclient.helper.account_helper.AccountHelper;
import pub.devrel.easypermissions.EasyPermissions;

public class RegistrationActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final int REQUEST_ACCOUNT_PICKER = 1000;
    private static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1001;

    private AccountHelper accountHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        YoutubeClientApplication app = (YoutubeClientApplication) getApplication();
        accountHelper = app.getAccountHelperFactory().create();

        findViewById(R.id.button_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHomeActivityIfPrerequisiteSatisfied();
            }
        });

        startHomeActivityIfPrerequisiteSatisfied();
    }

    private void startHomeActivityIfPrerequisiteSatisfied() {
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.GET_ACCOUNTS)) {
            requestPermission();
        } else if (!accountHelper.hasAccount()) {
            startActivityForResult(
                    accountHelper.createChooseAccountIntent(),
                    REQUEST_ACCOUNT_PICKER);
        } else {
            // TODO startActivity
            Toast.makeText(this, "Setup completed!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null && data.getExtras() != null) {
                    String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    accountHelper.saveAccount(accountName);
                    startHomeActivityIfPrerequisiteSatisfied();
                }
                break;
        }
    }

    private void requestPermission() {
        EasyPermissions.requestPermissions(
                this,
                "This app needs to access your Google account (via Contacts).",
                REQUEST_PERMISSION_GET_ACCOUNTS,
                Manifest.permission.GET_ACCOUNTS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        startHomeActivityIfPrerequisiteSatisfied();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        Log.d("RegistrationActivity", "onPermissionsDenied");
    }

}
