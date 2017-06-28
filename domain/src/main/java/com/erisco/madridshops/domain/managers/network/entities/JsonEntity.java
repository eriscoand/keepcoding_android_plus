package com.erisco.madridshops.domain.managers.network.entities;

import com.google.gson.annotations.SerializedName;

public class JsonEntity {
    @SerializedName("id") private long id;
    @SerializedName("name") private String name;
    @SerializedName("address") private String address;
    @SerializedName("description_es") private String description_es;
    @SerializedName("description_en") private String description_en;
    @SerializedName("gps_lat") private String gps_lat;
    @SerializedName("gps_lon") private String gps_lon;
    @SerializedName("img") private String img;
    @SerializedName("logo_img") private String logo_img;
    @SerializedName("url") private String url;
    @SerializedName("telephone") private String telephone;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription_es() {
        return description_es;
    }

    public String getDescription_en() {
        return description_en;
    }

    public String getGps_lat() {
        return gps_lat;
    }

    public String getGps_lon() {
        return gps_lon;
    }

    public String getImg() {
        return img;
    }

    public String getLogo_img() {
        return logo_img;
    }

    public String getUrl() {
        return url;
    }

    public String getTelephone() {
        return telephone;
    }
}
