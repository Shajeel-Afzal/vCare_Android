package com.ammi.ammicare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ammi.ammicare.utils.Consts;
import com.ammi.ammicare.utils.DialogUtils;
import com.ammi.ammicare.utils.PrefUtils;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppBaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AgeInputActivity.start(this);

        boolean isFirstRun = PrefUtils.getBoolean(this, Consts.KEY_IS_FIRST_RUN, true);
        if (isFirstRun && FirebaseAuth.getInstance().getCurrentUser() != null) {
            AgeInputActivity.start(this);
            PrefUtils.saveBoolean(this, Consts.KEY_IS_FIRST_RUN, false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout_item) {
            DialogUtils.showOkCancelDialog(this, "Logout", "Are you sure to logout from the app?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AppBaseActivity.logout(MainActivity.this);
                }
            }, null, "Logout", "Cancel");
        }
        return super.onOptionsItemSelected(item);
    }

    public void onGrowthRateClick(View view) {
        GrowthMonthsActivity.start(this);
    }
}
