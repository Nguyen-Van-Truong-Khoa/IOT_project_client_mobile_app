package com.example.myapp;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class custome_listview extends BaseAdapter {

    private JSONArray jsonArray;

    public custome_listview(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public JSONObject getItem(int position) {
        try {
            return jsonArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = View.inflate(parent.getContext(), R.layout.my_list, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.image);
            holder.dateTextView = view.findViewById(R.id.date);
            holder.timeTextView = view.findViewById(R.id.time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Get the JSON object at the specified position
        JSONObject jsonObject = getItem(position);
        if (jsonObject != null) {
            try {
                history_class history = new history_class(jsonObject);

                // Set image resource based on event type
                if (history.event_type.equals("0")) {
                    holder.imageView.setImageResource(R.drawable.car_out);
                } else {
                    holder.imageView.setImageResource(R.drawable.car_in);
                }

                holder.dateTextView.setText(String.format("Date: %s", history.date));
                holder.timeTextView.setText(String.format("Time: %s", history.time));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView dateTextView;
        TextView timeTextView;
    }
}
