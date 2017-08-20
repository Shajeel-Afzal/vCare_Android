package com.ammi.ammicare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ammi.ammicare.utils.Consts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AgeInputActivity extends AppBaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AgeInputActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_input);

        TextView mWelcomeTV = (TextView) findViewById(R.id.welcome_tv);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            finish();
            return;
        }


        String userName = "";
        if (FirebaseAuth.getInstance().getCurrentUser().getDisplayName() != null)
            userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        if (userName != null && !userName.isEmpty()) {
            mWelcomeTV.setText("Welcome " + userName + "!");
        } else {
            mWelcomeTV.setText("Welcome");
        }
    }

    public void onSaveAgeClick(View view) {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar dateOfBirth = Calendar.getInstance();
                        dateOfBirth.set(Calendar.YEAR, year);
                        dateOfBirth.set(Calendar.MONTH, monthOfYear);
                        dateOfBirth.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
                        String dob = formatter.format(dateOfBirth.getTime());

                        FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child(Consts.FIREBASE_LOCATION_CHILD_DATE_OF_BIRTH)
                                .setValue(dob);

                        Toast.makeText(AgeInputActivity.this, " Date of Birth Saved: " + dob, Toast.LENGTH_SHORT).show();

                        finish();
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
}
