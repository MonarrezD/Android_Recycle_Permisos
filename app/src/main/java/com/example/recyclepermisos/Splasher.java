package com.example.recyclepermisos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splasher extends AppCompatActivity {
    private int activityToStart = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasher);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                checkAllPermissions();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (activityToStart == 1) {
                            Intent i = new Intent(Splasher.this, respuesta.class);
                            startActivity(i);
                        } else {
                            Intent i = new Intent(Splasher.this, MainActivity.class);
                            startActivity(i);
                        }
                        finish();
                    }
                });
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 3000);
    }
    private void checkAllPermissions() {
        String[] permissionsToCheck = {
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        };

        boolean allPermissionsGranted = true;

        for (String permission : permissionsToCheck) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (allPermissionsGranted) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activityToStart = 1;
                }
            });
        }
    }

}