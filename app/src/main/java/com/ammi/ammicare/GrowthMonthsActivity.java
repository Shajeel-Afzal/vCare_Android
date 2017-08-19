package com.ammi.ammicare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ammi.ammicare.models.GrowthRateMonth;
import com.ammi.ammicare.utils.Consts;
import com.ammi.ammicare.view_holders.GrowthRateVH;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GrowthMonthsActivity extends AppBaseActivity {

    private RecyclerView mRV;
    private FirebaseRecyclerAdapter<GrowthRateMonth, GrowthRateVH> mAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, GrowthMonthsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_months);

        mRV = (RecyclerView) findViewById(R.id.recyclerview);

        mRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        DatabaseReference query = FirebaseDatabase.getInstance().getReference()
                .child(Consts.FIREBASE_LOCATION_GROWTH_MONTHS);
        query.keepSynced(true);

        mAdapter = new FirebaseRecyclerAdapter<GrowthRateMonth, GrowthRateVH>(GrowthRateMonth.class,
                R.layout.growth_month_layout_item, GrowthRateVH.class, query) {
            GrowthRateVH growthVH;

            @Override
            protected void populateViewHolder(GrowthRateVH viewHolder, GrowthRateMonth model, int position) {
                viewHolder.setData(model);
            }

            @Override
            public GrowthRateVH onCreateViewHolder(ViewGroup parent, int viewType) {
                growthVH = super.onCreateViewHolder(parent, viewType);
                growthVH.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {

                    }
                });
                return growthVH;
            }
        };

        mRV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRV.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        if (mAdapter != null)
            mAdapter.cleanup();

        super.onDestroy();
    }
}
