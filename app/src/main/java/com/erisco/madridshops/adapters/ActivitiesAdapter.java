package com.erisco.madridshops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erisco.madridshops.R;
import com.erisco.madridshops.domain.model.Activity.Activities;
import com.erisco.madridshops.domain.model.Activity.Activity;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;
import com.erisco.madridshops.views.OnElementClick;
import com.erisco.madridshops.views.RowViewHolder;

public class ActivitiesAdapter extends RecyclerView.Adapter<RowViewHolder> {
    private Activities activities;
    private LayoutInflater inflater;
    private OnElementClick<Activity> listener;

    public ActivitiesAdapter(final Activities activities, final Context context) {
        this.activities = activities;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_element, parent, false);

        RowViewHolder viewHolder = new RowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RowViewHolder row, final int position) {
        final Activity activity = this.activities.get(position);
        row.set(activity.getName(),activity.getLogoUrl());

        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.clickedOn(activity, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.activities != null) {
            return (int) this.activities.size();
        }
        return 0;
    }

    public void setOnClickListener(OnElementClick<Activity> listener) {
        this.listener = listener;
    }
}
