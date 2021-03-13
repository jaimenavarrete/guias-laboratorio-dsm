package com.udb.dsm.septimaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    TextView txtViewWelcome;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtViewWelcome = findViewById(R.id.txtViewWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        txtViewWelcome.setText("Bienvenido, " + user.getEmail());

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();

            Intent i = new Intent(DashboardActivity.this, Ejercicio2Activity.class);
            startActivity(i);
        });
    }
}
