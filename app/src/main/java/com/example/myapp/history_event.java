package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class history_event extends AppCompatActivity {

    private ListView historyTextView;
    private RequestQueue requestQueue;
    private String License_ID;

    custome_listview custome_listview;
    ListView listViewHistory;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyTextView = findViewById(R.id.historyTextView);
        requestQueue = Volley.newRequestQueue(this);

        // Get the username from the Intent
        Intent intent = getIntent();
        License_ID = intent.getStringExtra("License_ID");
        //License_ID = "30F5594";
        getHistoryEvent(License_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                Goto_activity(this, login.class);
                break;
            case R.id.history:
                Goto_activity(this, history_event.class);
                break;
            case R.id.status:
                Goto_activity(this, spot_status.class);
                break;
        }
        return true;
    }

    public void Goto_activity(Context context, Class<?> passClass) {
        Intent intent = new Intent(context, passClass);
        intent.putExtra("License_ID", License_ID);
        context.startActivity(intent);
    }
    private void getHistoryEvent(String licenseId) {
        String url = "https://iot-server-hl0a.onrender.com/history-event?license_id=" + licenseId;

        // No need to create a JSON object for the request body in a GET request

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //JSONArray eventsArray = response.getJSONArray("history");
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject event = response.getJSONObject(i);
//                            }
                        custome_listview = new custome_listview(response);

                        listViewHistory = findViewById(R.id.historyTextView);
                        listViewHistory.setAdapter(custome_listview);
                        //historyTextView.setText(historyBuilder.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(history_event.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

}
