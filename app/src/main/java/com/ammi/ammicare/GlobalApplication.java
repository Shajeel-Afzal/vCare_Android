package com.ammi.ammicare;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by shajeelafzal on 19/08/2017.
 */

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().setPersistenceCacheSizeBytes(104857600);
    }
}
