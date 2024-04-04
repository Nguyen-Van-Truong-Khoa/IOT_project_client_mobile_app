package com.example.myapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class history_class {
//    String id;
    String event_type;
    String timestamp;
    String date;
    String time;
    @RequiresApi(api = Build.VERSION_CODES.O)
    history_class(JSONObject historyObject) throws JSONException {
        //this.id = historyObject.getString("ID");
        this.event_type = historyObject.getString("Event_Type");
        this.timestamp = historyObject.getString("Timestamp");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        LocalDateTime timestamp = LocalDateTime.parse(this.timestamp, formatter);

        this.date = timestamp.toLocalDate().toString();
        this.time = timestamp.toLocalTime().toString();
    }

}
