package com.erisco.madridshops.domain.managers.db.ActivityDAO;

public class ActivityDBConstants {
    public static final String TABLE_ACTIVITY = "ACTIVITY";

    // Table field constants
    public static final String KEY_ACTIVITY_ID = "_id";
    public static final String KEY_ACTIVITY_NAME = "NAME";
    public static final String KEY_ACTIVITY_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_ACTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";

    public static final String KEY_ACTIVITY_ADDRESS = "ADDRESS";
    public static final String KEY_ACTIVITY_URL = "URL";
    public static final String KEY_ACTIVITY_DESCRIPTION = "DESCRIPTION";

    public static final String KEY_ACTIVITY_LATITUDE = "latitude";
    public static final String KEY_ACTIVITY_LONGITUDE = "longitude";

    public static final String[] ALL_COLUMNS = {
            KEY_ACTIVITY_ID,
            KEY_ACTIVITY_NAME,
            KEY_ACTIVITY_IMAGE_URL,
            KEY_ACTIVITY_LOGO_IMAGE_URL,
            KEY_ACTIVITY_ADDRESS,
            KEY_ACTIVITY_URL,
            KEY_ACTIVITY_DESCRIPTION,
            KEY_ACTIVITY_LATITUDE,
            KEY_ACTIVITY_LONGITUDE
    };

    public static final String SQL_SCRIPT_CREATE_ACTIVITY_TABLE =
            "create table " + TABLE_ACTIVITY
                    + "( "
                    + KEY_ACTIVITY_ID + " integer primary key autoincrement, "
                    + KEY_ACTIVITY_NAME + " text not null,"
                    + KEY_ACTIVITY_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_ADDRESS + " text,"
                    + KEY_ACTIVITY_URL + " text,"
                    + KEY_ACTIVITY_LATITUDE + " real,"
                    + KEY_ACTIVITY_LONGITUDE + " real, "
                    + KEY_ACTIVITY_DESCRIPTION + " text "
                    + ");";

    public static final String DROP_DATABASE_SCRIPTS = "";
    public static final String UPDATE_DATABASE_SCRIPTS = "";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_ACTIVITY_TABLE
    };
}
