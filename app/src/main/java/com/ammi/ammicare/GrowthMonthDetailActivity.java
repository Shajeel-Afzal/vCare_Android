package com.ammi.ammicare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ammi.ammicare.utils.Consts;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GrowthMonthDetailActivity extends AppBaseActivity {

    private String mGrowthRateMonth;
    private RecyclerView mRV;

    public static void start(Context context, String month) {
        Intent starter = new Intent(context, GrowthMonthDetailActivity.class);
        starter.putExtra(Consts.KEY_GROWTH_RATE_MONTH, month);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_month_detail);

        mGrowthRateMonth = getIntent().getStringExtra(Consts.KEY_GROWTH_RATE_MONTH);

        mRV = (RecyclerView) findViewById(R.id.recyclerview);

        mRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        DatabaseReference query = FirebaseDatabase.getInstance().getReference()
                .child(Consts.FIREBASE_LOCATION_GROWTH_MONTHS_DETAIL)
                .child(mGrowthRateMonth);
        query.keepSynced(true);


    }
}
