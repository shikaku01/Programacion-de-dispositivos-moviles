package com.example.arreglos_y_tiempo_de_ejecucin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

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
        // Crear un arreglo con 1 millón de numeros aleatorios
        int[] numeros = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            numeros[i] = (int) (Math.random()*1000000);
        }

        // Medir el tiempo de inicio
        long startTime = System.currentTimeMillis();

        // Paso 4: Ordenar los elementos
        Arrays.sort(numeros);

        // Medir el tiempo de fin
        long endTime = System.currentTimeMillis();

        // Paso 5: Calcular el tiempo de inicio a fin
        long elapsedTime = endTime - startTime;

        // Paso 6: Imprimir los resultados en Logcat
        Log.d("TiempoOrdenacion", "Tiempo de inicio: " + startTime + " milisegundos");
        Log.d("TiempoOrdenacion", "Tiempo de fin: " + endTime + " milisegundos");
        Log.d("TiempoOrdenacion", "Tiempo total de ordenación: " + elapsedTime + " milisegundos");

        // Mostrar los primeros 5 elementos del arreglo ordenado para verificar
        Log.d("TiempoOrdenacion", "Primeros 5 elementos del arreglo ordenado: ");
        for (int i = 0; i < 5; i++) {
            Log.d("TiempoOrdenacion", "Elemento " + i + ": " + numeros[i]);
        }
    }
}