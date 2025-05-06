package com.example.secuencia_fibonacci;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvf;
    Button botonavanzar, botonregresar;
    int inicio = 0;
    Map<Integer, Long> memo = new HashMap<>();

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

        tvf = findViewById(R.id.tvf);
        botonavanzar = findViewById(R.id.botonavanzar);
        botonregresar = findViewById(R.id.botonregresar);

        tvf.setText(String.valueOf(fibonacci(inicio)));

        botonavanzar.setOnClickListener(v -> {
            inicio++;
            tvf.setText(String.valueOf(fibonacci(inicio)));
        });

        botonregresar.setOnClickListener(v -> {
            if (inicio > 0) {
                inicio--;
                tvf.setText(String.valueOf(fibonacci(inicio)));
            }
        });
    }
    private long fibonacci(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long a = 0, b = 1, result = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 0) result = 0;
            else if (i == 1) result = 1;
            else result = a + b;

            memo.put(i, result);
            a = b;
            b = result;
        }
        return result;
    }
}