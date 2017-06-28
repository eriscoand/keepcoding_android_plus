package com.erisco.madridshops.domain.managers.network.jsonparser;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import com.erisco.madridshops.domain.managers.network.entities.JsonEntity;
import com.erisco.madridshops.domain.managers.network.entities.JsonResponseEntity;

public class JsonParser {
    public List<JsonEntity> parse(@NonNull final String response) {
        if (response == null) {
            return null;
        }

        List<JsonEntity> jsonEntities = null;

        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = new StringReader(response);
            JsonResponseEntity jsonResponseEntity = gson.fromJson(reader, JsonResponseEntity.class);
            jsonEntities = jsonResponseEntity.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonEntities;
    }
}
