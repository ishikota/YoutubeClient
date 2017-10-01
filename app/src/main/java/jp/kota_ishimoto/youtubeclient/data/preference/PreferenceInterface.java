package jp.kota_ishimoto.youtubeclient.data.preference;

public interface PreferenceInterface {

    String getString(String key);

    void saveString(String key, String value);

    void delete(String key);

}
