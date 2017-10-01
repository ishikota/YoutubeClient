package jp.kota_ishimoto.youtubeclient.ui.registration

import android.Manifest
import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import jp.kota_ishimoto.youtubeclient.R
import jp.kota_ishimoto.youtubeclient.helper.account_helper.AccountHelper
import jp.kota_ishimoto.youtubeclient.ui.YoutubeClientApplication
import pub.devrel.easypermissions.EasyPermissions

class RegistrationActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var accountHelper: AccountHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val app = application as YoutubeClientApplication
        accountHelper = app.getAccountHelperFactory().create()

        findViewById(R.id.button_retry).setOnClickListener {
            startHomeActivityIfPrerequisiteSatisfied()
        }

        startHomeActivityIfPrerequisiteSatisfied()
    }

    private fun startHomeActivityIfPrerequisiteSatisfied() {
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.GET_ACCOUNTS)) {
            requestPermission()
        } else if (!accountHelper.hasAccount()) {
            startActivityForResult(
                    accountHelper.createChooseAccountIntent(),
                    REQUEST_ACCOUNT_PICKER)
        } else {
            // TODO startActivity
            Toast.makeText(this, "Setup completed!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_ACCOUNT_PICKER ->
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val accountName = data?.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
                        accountName?.let {
                            accountHelper.saveAccount(accountName)
                            startHomeActivityIfPrerequisiteSatisfied()
                        }
                    }
                    else -> {
                        Log.d("RegistrationActivity", "failed to pick account")
                    }
                }
        }
    }

    private fun requestPermission() {
        EasyPermissions.requestPermissions(
                this,
                "This app needs to access your Google account (via Contacts).",
                REQUEST_PERMISSION_GET_ACCOUNTS,
                Manifest.permission.GET_ACCOUNTS)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, list: List<String>) {
        startHomeActivityIfPrerequisiteSatisfied()
    }

    override fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        Log.d("RegistrationActivity", "onPermissionsDenied")
    }

    companion object {
        private val REQUEST_ACCOUNT_PICKER = 1000
        private val REQUEST_PERMISSION_GET_ACCOUNTS = 1001
    }

}
