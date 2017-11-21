package stijnjj.hueapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jaap-Jan on 21-11-2017.
 */

public class Settings {
    private static final String PreferencesName = "ApiSettings";
    SharedPreferences _preferences;
    SharedPreferences.Editor _editor;
    private static Settings _instance;

    private String _username, _location;
    private int _port;

    private Settings(Context c) {
        _preferences = c.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        _editor = _preferences.edit();
        _username = _preferences.getString("username", "newdeveloper");
        _location = _preferences.getString("location", "localhost");
        _port = _preferences.getInt("port", 8000);
    }

    public static Settings createInstance(Context c) {
        return _instance = new Settings(c);
    }

    public static Settings getInstance() {
        return _instance;
    }

    public String getUsername() {
        return _username;
    }

    public String getLocation() {
        return _location;
    }

    public int getPort() {
        return _port;
    }

    public void setUsername(String username) {
        _username = username;
        _editor.putString("username", username);
        _editor.commit();
    }

    public void setLocation(String location) {
        _location = location;
        _editor.putString("location", location);
        _editor.commit();
    }

    public void setPort(int port) {
        _port = port;
        _editor.putInt("port", port);
        _editor.commit();
    }
}
