package jp.kota_ishimoto.youtubeclient.data.preference;

import android.content.Context;

public class PreferenceFactory {

    private final Context context;

    public PreferenceFactory(Context context) {
        this.context = context;
    }

    public PreferenceInterface create() {
        return new AppPreference(context);
    }

}
