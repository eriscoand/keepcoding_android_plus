package com.erisco.madridshops.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import com.erisco.madridshops.R;
import com.erisco.madridshops.domain.model.Shop.Shop;

public class RowViewHolder extends RecyclerView.ViewHolder {

    private TextView rowTitleTextView;
    private ImageView rowImageView;
    WeakReference<Context> context;

    public RowViewHolder(View row) {
        super(row);

        this.context = new WeakReference<>(row.getContext());

        rowTitleTextView = (TextView) row.findViewById(R.id.row_element__element_name);
        rowImageView = (ImageView) row.findViewById(R.id.row_element__element_logo);
    }

    public void set(@NonNull final String title, @NonNull final String imageUrl) {

        rowTitleTextView.setText(title);
        Picasso.with(context.get()).
                load(imageUrl).
                placeholder(R.drawable.placeholder).
                into(rowImageView);

    }
}
