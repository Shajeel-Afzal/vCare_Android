package com.ammi.ammicare;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ammi.ammicare.utils.Consts;
import com.ammi.ammicare.utils.PrefUtils;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

public class AppBaseActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    if (!PrefUtils.getBoolean(AppBaseActivity.this, Consts.LOGIN_LATER_KEY)) {
                        /* Move user to AppLoginActivity, and remove the backstack */
                        Intent intent = new Intent(AppBaseActivity.this, AppLoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };

        if (!((this instanceof AppLoginActivity)) && !((this instanceof AppLoginActivity)))
            mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null && !((this instanceof AppBaseActivity))) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

    /**
     * Logs out the user from their current session and starts AppLoginActivity.
     * Also disconnects the mGoogleApiClient if connected and provider is Google
     */
    public static void logout(Context context) {

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Toast.makeText(context, "You are already signed out!", Toast.LENGTH_SHORT).show();
            return;
        }

        /** Cancel all Notifications shown for the user that is going to logout. */
        NotificationManager nManager = ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
        nManager.cancelAll();

        if (FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
            FirebaseAuth.getInstance().signOut();
            regenerateTheToken();
            return;
        }

        String provider = FirebaseAuth.getInstance().getCurrentUser().getProviderData().get(1).getProviderId();

        /* Logout if mProvider is not null */
        if (provider != null) {
            FirebaseAuth.getInstance().signOut();

            PrefUtils.clearAllPrefs(context);

            if (provider.equals(AuthUI.GOOGLE_PROVIDER)) {
                /* Logout from Google+ */
//                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                        new ResultCallback<Status>() {
//                            @Override
//                            public void onResult(Status status) {
//                                //nothing
//                            }
//                        });
            } else if (provider.equals(AuthUI.FACEBOOK_PROVIDER)) {
            }
        }

        regenerateTheToken();
    }

    private static void regenerateTheToken() {
        // Regenerate the Token.
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                    FirebaseInstanceId.getInstance().getToken();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}