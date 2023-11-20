package com.example.tarea24oscargomez;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import com.example.tarea24oscargomez.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    private ImageView imageView;
    private MiBasedeDatos miBasedeDatos;
    EditText editTextFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        editTextFileName=findViewById(R.id.editTextFileName);

        mainBinding.btnClearSign.setOnClickListener(view -> {
            mainBinding.signatureView.clearCanvas();
        });
        miBasedeDatos = new MiBasedeDatos(this);

        mainBinding.btnLoadInIv.setOnClickListener(view -> {

            Bitmap signBitmap = mainBinding.signatureView.getSignatureBitmap();
            if (signBitmap != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                signBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // Aquí debes insertar el byte array en la base de datos utilizando SQLiteOpenHelper o algún otro método de acceso a la base de datos
                // Por ejemplo:
                SQLiteDatabase db = miBasedeDatos.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("firma", byteArray);
                values.put("nombre", editTextFileName.getText().toString());
                long result = db.insert(MiBasedeDatos.tableName, null, values);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Firma guardada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al guardar la firma", Toast.LENGTH_SHORT).show();
                }
            }

        });

        Button btnVerRegistros = findViewById(R.id.btnVerRegistros);
        btnVerRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityRegistrosFirmas.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        miBasedeDatos.close();
        super.onDestroy();
    }
}