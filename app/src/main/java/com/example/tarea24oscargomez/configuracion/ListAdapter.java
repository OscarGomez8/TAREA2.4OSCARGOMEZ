package com.example.tarea24oscargomez.configuracion;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tarea24oscargomez.R;
import com.example.tarea24oscargomez.MiBasedeDatos;

import java.util.List;

public class ListAdapter extends ArrayAdapter<objetoFirmas> implements View.OnClickListener{
    private List<objetoFirmas> firma;
    private Context context;
    MiBasedeDatos conexion;

    public ListAdapter(@NonNull Context context, List<objetoFirmas> firma) {
       super(context, R.layout.activity_signaturess, firma);
       this.context = context;
       this.firma = firma;
    }

    @Override
    public void onClick(View v) {

    }
    public static class ViewHolder{
        ImageView firma_dibujada;
        TextView txt_name;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        objetoFirmas firmas = firma.get(position);
        ViewHolder viewHolder;
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_signaturess,null);
        }
        ImageView imagen = view.findViewById(R.id.firma_dibujada);
        TextView txt_name = view.findViewById(R.id.txt_name);

        imagen.setImageBitmap(obtenerImagen(firmas.getId()));
        txt_name.setText(firmas.getNombre());

        return view;
    }

    private Bitmap obtenerImagen(String id) {
        conexion = new MiBasedeDatos(context);
        SQLiteDatabase db = conexion.getReadableDatabase();
        Bitmap bitmap;
        String selectQuery = "SELECT firma FROM firmas WHERE id = ?";
        // Ejecuta la consulta
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        // Verifica si se encontraron resultados
        if (cursor.moveToFirst()) {
            // Obtiene los datos de la imagen en forma de arreglo de bytes
            byte[] imageData = cursor.getBlob(cursor.getColumnIndexOrThrow("firma"));
            // Convierte los datos de la imagen en un objeto Bitmap
            bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        }
        else{
            bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.imageb);
        }
        // Cierra el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return bitmap;
    }
}
