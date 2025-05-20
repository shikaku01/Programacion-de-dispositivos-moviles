package com.example.calorias_duendes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ConteoCalorias";
    private static final int REQUEST_PERMISSION_READ_STORAGE = 100;

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

        // Verificar permiso de lectura
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_READ_STORAGE);
        } else {
            leerArchivoYProcesar();
        }
    }

    // Manejo de resultado del permiso
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_READ_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                leerArchivoYProcesar();
            } else {
                Log.e(TAG, "Permiso denegado para leer almacenamiento externo");
            }
        }
    }

    private void leerArchivoYProcesar() {
        // Ruta a la carpeta Documents
        File documentsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File archivo = new File(documentsDir, "input datos duendes.txt");

        String contenido = readFileAsString(archivo.getAbsolutePath());

        if (!contenido.isEmpty()) {
            calcularYMostrarMayorCalorias(contenido);
        } else {
            Log.e(TAG, "El archivo está vacío o no se pudo leer.");
        }
    }

    // Método para leer archivo como string
    public static String readFileAsString(String filePath) {
        StringBuilder result = new StringBuilder();
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                int data;
                while ((data = fis.read()) != -1) {
                    result.append((char) data);
                }
            } catch (Exception e) {
                Log.d(TAG, "Error leyendo archivo: " + e.toString());
            } finally {
                try {
                    if (fis != null) fis.close();
                } catch (IOException ignored) {
                }
            }
        } else {
            Log.d(TAG, "Archivo no encontrado en: " + filePath);
        }
        return result.toString();
    }

    private void calcularYMostrarMayorCalorias(String input) {
        String[] lineas = input.split("\n");
        int maxCalorias = 0;
        int sumaActual = 0;
        int elfoActual = 1;
        int elfoConMasCalorias = 1;

        for (String linea : lineas) {
            if (linea.trim().isEmpty()) {
                Log.i(TAG, "Elfo #" + elfoActual + " lleva " + sumaActual + " calorías.");
                if (sumaActual > maxCalorias) {
                    maxCalorias = sumaActual;
                    elfoConMasCalorias = elfoActual;
                }
                sumaActual = 0;
                elfoActual++;
            } else {
                sumaActual += Integer.parseInt(linea.trim());
            }
        }

        if (sumaActual > 0) {
            Log.i(TAG, "Elfo #" + elfoActual + " lleva " + sumaActual + " calorías.");
            if (sumaActual > maxCalorias) {
                maxCalorias = sumaActual;
                elfoConMasCalorias = elfoActual;
            }
        }

        Log.i(TAG, "-----------------------------------------");
        Log.i(TAG, "El Elfo #" + elfoConMasCalorias + " lleva la mayor cantidad de calorías: " + maxCalorias);
    }
}