package com.example.userlogin;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private TextView disname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

            String username = getIntent().getStringExtra("username");
            this.disname = findViewById(R.id.disuser);
            Toast.makeText(DashboardActivity.this, "Welcome, " + username + "!", Toast.LENGTH_SHORT).show();
            disname.setText("Welcome, " + username + "!");
    }
}