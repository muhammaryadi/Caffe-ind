package com.example.airin;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "AirinSession";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_PASSWORD = "user_password";
    private static final String KEY_IS_LOGGED = "is_logged";
    private static final String KEY_KOPI_ID = "kopi_id";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUserId(String userId) {
        editor.putString(KEY_USER_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    public void setIsLogged(boolean isLogged) {
        editor.putBoolean(KEY_IS_LOGGED, isLogged);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED, false);
    }

    // New method for logout
    public void logoutUser() {
        // Clear all stored data in the session
        editor.clear();
        editor.apply();
    }

    public String getPassword() { return sharedPreferences.getString(KEY_USER_PASSWORD, null);}

    public void setKopiId(String kopiId) {
        editor.putString(KEY_KOPI_ID, kopiId);
        editor.apply();
    }

    public String getKopiId() {
        return sharedPreferences.getString(KEY_KOPI_ID, null);
    }
}
