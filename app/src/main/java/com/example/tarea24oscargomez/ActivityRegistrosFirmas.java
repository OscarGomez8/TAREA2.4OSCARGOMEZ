package com.example.tarea24oscargomez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.tarea24oscargomez.configuracion.ListAdapter;
import com.example.tarea24oscargomez.configuracion.objetoFirmas;


public class ActivityRegistrosFirmas extends AppCompatActivity {

    ListView listView;
    List<objetoFirmas> mData = new ArrayList<>();
    ListAdapter mAdapter;
    MiBasedeDatos conexion;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_firmas);

        conexion = new MiBasedeDatos(this);

        backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed(); // Vuelve a la actividad anterior al presionar el botón
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        obtenerTabla();
        mAdapter = new ListAdapter(this,mData);
        listView.setAdapter(mAdapter);
    }

    private void obtenerTabla() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        objetoFirmas firmas = null;
        //Cursor de base de datos
        Cursor cursor = db.rawQuery(MiBasedeDatos.SelectTable,null);

        //Recorremos el cursor
        while (cursor.moveToNext()){
            firmas = new objetoFirmas();
            firmas.setId(cursor.getString(0));
            firmas.setNombre(cursor.getString(2));
            mData.add(firmas);
        }
        cursor.close();
    }
}