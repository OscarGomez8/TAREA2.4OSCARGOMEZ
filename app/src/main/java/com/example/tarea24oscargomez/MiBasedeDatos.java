package com.example.tarea24oscargomez;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MiBasedeDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "firmasTarea2.4.db";
    private static final int DATABASE_VERSION = 1;
    public static  final String tableName = "firmas";

    public MiBasedeDatos(Context context) {
        super(context, NOMBRE_BD, null, DATABASE_VERSION);
    }

    public static final String SelectTable= "SELECT * FROM " + tableName;

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define la sentencia SQL para crear la tabla
        String createTableQuery = "CREATE TABLE firmas(id INTEGER PRIMARY KEY AUTOINCREMENT, firma BLOB, nombre TEXT)";

        // Ejecuta la sentencia SQL para crear la tabla
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
