package jp.kota_ishimoto.youtubeclient.data.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

class AppPreference implements PreferenceInterface {

    private static final String TAG = AppPreference.class.getSimpleName();

    private static final String PREF_FILE_NAME = "YoutubeClient";

    private final Context context;

    AppPreference(Context context) {
        this.context = context;
    }

    @Override
    public String getString(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    @Override
    public void saveString(String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
        Log.d(TAG, String.format("saved string [ %s ] to preference with key [ %s ].", value, key));
    }

    @Override
    public void delete(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
        Log.d(TAG, String.format("deleted from preference with key [ %s ].", key));
    }

}
