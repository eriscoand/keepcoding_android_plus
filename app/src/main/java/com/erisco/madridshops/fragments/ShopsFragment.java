package com.erisco.madridshops.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erisco.madridshops.R;
import com.erisco.madridshops.adapters.ShopsAdapter;
import com.erisco.madridshops.domain.model.Shop.Shop;
import com.erisco.madridshops.domain.model.Shop.Shops;
import com.erisco.madridshops.views.OnElementClick;


public class ShopsFragment extends Fragment {

    private OnElementClick<Shop> listener;

    private RecyclerView shopsRecyclerView;
    private ShopsAdapter adapter;
    private Shops shops;

    public ShopsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        shopsRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_shops__recycler_view);
        shopsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void setShops(Shops shops) {
        this.shops = shops;

        adapter = new ShopsAdapter(shops, getActivity());
        shopsRecyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop shop, int position) {
                Log.d("Click", shop.getName());
                if (listener != null) {
                    ShopsFragment.this.listener.clickedOn(shop, position);
                }
            }
        });
    }

    public void setOnElementClickListener(OnElementClick<Shop> listener) {
        this.listener = listener;
    }

}
