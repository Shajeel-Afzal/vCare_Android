package com.ammi.ammicare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ammi.ammicare.models.UserModel;
import com.ammi.ammicare.utils.Consts;
import com.ammi.ammicare.utils.PrefUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppLoginActivity extends AppBaseActivity {

    private static final int RC_SIGN_IN = 101;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (BuildConfig.DEBUG) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeathOnNetwork()
                    .build());
        }
    }

    public void loginNow(View view) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.mipmap.ic_launcher)
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .build(),
                RC_SIGN_IN);
    }

    public void loginLater(View view) {
        signInAnonymously();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                PrefUtils.saveBoolean(this, Consts.LOGIN_LATER_KEY, false);

                saveUserInformation();
                return;
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    showSnackbar(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackbar(R.string.no_internet_connection);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackbar(R.string.unknown_error);
                    return;
                }
            }

            showSnackbar(R.string.unknown_sign_in_response);
        }
    }

    private void showSnackbar(int sign_in_cancelled) {
        Snackbar.make(findViewById(android.R.id.content), getString(sign_in_cancelled), Snackbar.LENGTH_LONG).show();
    }

    public void saveUserInformation() {
        mProgressDialog = ProgressDialog.show(this, getString(R.string.please_wait), getString(R.string.setting_up_account));

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final UserModel userModel = new UserModel();
        if (firebaseUser != null) {
            userModel.setEmail(firebaseUser.getEmail());
            userModel.setName(firebaseUser.getDisplayName());
            userModel.setUid(firebaseUser.getUid());

            if (!firebaseUser.isAnonymous()) {
                UserInfo providerData = firebaseUser.getProviderData().get(1);
                if (providerData.getPhotoUrl() != null) {
                    userModel.setProfileImageLink(providerData.getPhotoUrl().toString());
                }

                userModel.setProvider(firebaseUser.getProviderData().get(1).getProviderId());
            } else {
                userModel.setProvider(Consts.ANONYMOUS_PROVIDER);
            }

            HashMap<String, Object> userTaskMap = (HashMap<String, Object>) new ObjectMapper().convertValue(userModel, Map.class);

            // INSERT USER IN THE DATABASE
            FirebaseDatabase.getInstance()
                    .getReference()
                    .child(Consts.FIREBASE_LOCATION_USERS)
                    .child(firebaseUser.getUid())
                    .updateChildren(userTaskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    mProgressDialog.dismiss();

                    if (task.isSuccessful()) {
                        MainActivity.start(AppLoginActivity.this);
                        finish();
                    } else {
                        MainActivity.start(AppLoginActivity.this);
                        finish();
                    }
                }
            });
        } else {
            mProgressDialog.dismiss();
            MainActivity.start(this);
        }
    }

    public void signInAnonymously() {
        mProgressDialog = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.setting_up_anonymous_account), true);
        FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("", "signInAnonymously:onComplete:" + task.isSuccessful());
                        if (task.isSuccessful()) {
                            // TODO: Log the successful anonymous login in the analytics
                            saveUserInformation();
                        }
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        else if (!task.isSuccessful()) {
                            // TODO: Log exception in the analytics
                            mProgressDialog.dismiss();
                            Log.d("", "signInAnonymously", task.getException());
                            showSnackbar(R.string.authentication_failed_please_try_later);
                        }
                    }
                });
    }


    private int mIsActive;

    public boolean isActive() {
        return mIsActive == 1;
    }

    protected void sendEvent(String category, String action, String label) {
        // Get tracker.
    }

    protected void sendEvent(String category, String action) {
        // Get tracker.
    }

    protected void sendEvent(String category) {
        // Get tracker.
    }

    protected void sendAction(String action) {
        // Get tracker.
    }

    protected void sendNewsShareAction(String newsTitle) {
    }

    @Override
    public void onStart() {
        super.onStart();
        // Get an Analytics tracker to report app starts &amp; uncaught
        // exceptions etc.
        mIsActive = 1;
    }

    @Override
    public void onStop() {
        super.onStop();
        // Get an Analytics tracker to report app starts &amp; uncaught
        // exceptions etc.
        mIsActive = 0;
    }

    protected void sendScreenName(String name) {
        // Get tracker.
    }

}
