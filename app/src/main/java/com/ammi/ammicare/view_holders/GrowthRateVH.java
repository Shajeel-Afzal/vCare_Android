package com.ammi.ammicare.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ammi.ammicare.OnRecyclerViewItemClickListener;
import com.ammi.ammicare.R;
import com.ammi.ammicare.models.GrowthRateMonth;

/**
 * Created by shajeelafzal on 19/08/2017.
 */

public class GrowthRateVH extends RecyclerView.ViewHolder {

    private TextView growthMonthTV;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private GrowthRateMonth data;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public GrowthRateVH(View itemView) {
        super(itemView);
        growthMonthTV = (TextView) itemView.findViewById(R.id.growth_month_tv);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerViewItemClickListener != null)
                    onRecyclerViewItemClickListener.onItemClicked(getAdapterPosition());
            }
        });
    }

    public void setData(GrowthRateMonth data) {
        this.data = data;
        growthMonthTV.setText(data.getMonth());
    }
}
