package com.erisco.madridshops.domain.managers.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponseEntity {
    @SerializedName("result") private List<JsonEntity> result;

    public List<JsonEntity> getResult() {
        return result;
    }
}
