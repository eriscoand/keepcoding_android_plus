package com.erisco.madridshops.domain.managers.network.managers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.WeakReference;
import java.util.List;

import com.erisco.madridshops.domain.*;
import com.erisco.madridshops.domain.managers.network.entities.JsonEntity;
import com.erisco.madridshops.domain.managers.network.jsonparser.JsonParser;

public class ListManagerImplementation implements NetworkManager {

    WeakReference<Context> weakContext;

    public ListManagerImplementation(Context context) {
        weakContext = new WeakReference<Context>(context);
    }

    @Override
    public void getListFromServer(@NonNull final String url, @NonNull final ListManagerCompletion completion, @Nullable final ManagerErrorCompletion errorCompletion) {
        RequestQueue queue = Volley.newRequestQueue(weakContext.get());

        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("JSON", response);

                        JsonParser parser = new JsonParser();
                        List<JsonEntity> entities = parser.parse(response);

                        if (completion != null) {
                            completion.completion(entities);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", error.toString());
                        if (errorCompletion != null) {
                            errorCompletion.onError(error.getMessage());
                        }
                    }
                }
        );
        queue.add(request);
    }
}
