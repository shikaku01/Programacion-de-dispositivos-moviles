package com.example.ciclo_de_vida;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements Runnable {
    TextView tv1;
    private static final String TAG = "CicloDeVida";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toast.makeText(this, "ejecutando el onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "ejecutando el onCreate");
        tv1 = findViewById(R.id.tv1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "ejecutando el onStart", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "ejecutando el onStart");
        run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "ejecutando el onPause", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "ejecutando el onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "ejecutando el onRestart", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "ejecutando el onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "ejecutando el onResume", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "ejecutando el onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "ejecutando el onStop", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "ejecutando el onStop");
        tv1.removeCallbacks(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "ejecutando el onDestroy", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "ejecutando el onDestroy");
    }

    @Override
    public void run() {
        int x = Integer.parseInt(tv1.getText().toString()) + 1;
        tv1.setText(String.valueOf(x));
        tv1.postDelayed(this, 1000);
    }
}