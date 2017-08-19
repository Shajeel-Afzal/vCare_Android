package com.ammi.ammicare.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by shajeel1 on 11/04/15.
 */
public class PrefUtils {

    public static final String IS_PREMIUM_KEY = "is_pro_user";
    private static final String PROFILE_PICTURE_URL = "profile_picture_url";
    private static final String FIRST_NAME_KEY = "first_name_key";
    private static final String FACEBOOK_USER_KEY = "facebook_user_key";
    private static final String USER_ID_KEY = "user_id_key";
    private static final String USER_EMAIL_KEY = "user_email_key";

    private static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "app_prefs";
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void saveFirstName(Context context, String firstName) {
        putString(context, FIRST_NAME_KEY, firstName);
    }

    public static String getFirstName(Context context) {
        return getString(context, FIRST_NAME_KEY, "");
    }

    public static void saveProfilePictureUrl(Context context, String url) {
        putString(context, PROFILE_PICTURE_URL, url);
    }

    public static String getProfilePictureUrl(Context context) {
        return getString(context, PROFILE_PICTURE_URL, "");
    }

    public static void putString(Context context, String key, String value) {
        if (context == null)
            return;

        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static boolean isPremiumUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        return prefs.getBoolean(IS_PREMIUM_KEY, false);
    }

    public static void setIsPremiumUser(Context context, boolean value) {
        saveBoolean(context, IS_PREMIUM_KEY, value);
    }

    public static void saveInteger(Context context, String key, Integer value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInteger(Context context, String key) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getInt(key, 0);
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        return prefs.getBoolean(key, defaultValue);
    }

    public static int getInteger(Context context, String key, int defaultValue) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getInt(key, defaultValue);
    }

    public static void clearAllPrefs(Context context) {
        context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE).edit().clear().apply();
    }


}

