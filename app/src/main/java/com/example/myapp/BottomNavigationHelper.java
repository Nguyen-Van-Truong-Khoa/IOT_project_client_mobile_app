package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationHelper {

    public static void setupBottomNavigation(BottomNavigationView bottomNavigation, final Context context) {
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        Goto_activity(context, login.class);
                        return true;
                    case R.id.history:
                        Goto_activity(context, history_event.class);
                        return true;
                    case R.id.status:
                        Goto_activity(context, spot_status.class);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public static void Goto_activity(Context context, Class<?> passClass) {
        Intent intent = new Intent(context, passClass);
        context.startActivity(intent);
    }
}
