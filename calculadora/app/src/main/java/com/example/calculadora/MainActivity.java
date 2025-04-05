package com.example.calculadora;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText numero1, numero2;
    private TextView resultado;

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
        this.numero1 = findViewById(R.id.numero1);
        this.numero2 = findViewById(R.id.numero2);
        this.resultado = findViewById(R.id.resultado);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.Suma) {
            resultado.setText(String.valueOf(sumar()));
            Log.d("resultado", "Resultado de la suma: " + sumar());
        } else if (view.getId() == R.id.Resta) {
            resultado.setText(String.valueOf(restar()));
            Log.d("resultado", "Resultado de la resta: " + restar());
        } else if (view.getId() == R.id.Multiplicacion) {
            resultado.setText(String.valueOf(multiplicar()));
            Log.d("resultado", "Resultado de la multiplicacion: " + multiplicar());
        } else if (view.getId() == R.id.Divicion) {
            resultado.setText(String.valueOf(dividir()));
            Log.d("resultado", "Resultado de la divicion: " + dividir());
        }
    }

    //metodo sumar
    public double sumar() {
        double resultado = 0;
        resultado = Double.parseDouble(this.numero1.getText().toString().trim()) + Double.parseDouble(this.numero2.getText().toString().trim());
        return resultado;
    }

    //metodo restar
    public double restar() {
        double resultado = 0;
        resultado = Double.parseDouble(this.numero1.getText().toString().trim()) - Double.parseDouble(this.numero2.getText().toString().trim());
        return resultado;
    }

    //metodo multiplicar
    public double multiplicar() {
        double resultado = 0;
        resultado = Double.parseDouble(this.numero1.getText().toString().trim()) * Double.parseDouble(this.numero2.getText().toString().trim());
        return resultado;
    }

    //metodo dividir
    public double dividir() {
        double resultado = 0;
        double num1 = 0;
        double num2 = 0;
        num1 = Double.parseDouble(this.numero1.getText().toString().trim());
        num2 = Double.parseDouble(this.numero2.getText().toString().trim());
        if (num2 == 0) {
            Log.e("resultado", "Error: División por cero (NaN)");
            Toast.makeText(this, "NaN", Toast.LENGTH_LONG).show();
        } else {
            resultado = num1 / num2;
        }
        return resultado;
    }
}