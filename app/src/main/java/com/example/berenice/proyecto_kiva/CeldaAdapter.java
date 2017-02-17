package com.example.berenice.proyecto_kiva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Berenice on 17/02/2017.
 */

public class CeldaAdapter extends ArrayAdapter<JSONObject> {
    public CeldaAdapter (Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }

    public CeldaAdapter(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda,null);
        }

        TextView titulo = (TextView) celda.findViewById(R.id.titulo);
        TextView subtitulo=(TextView) celda.findViewById(R.id.subTitulo);
        TextView descripcion=(TextView) celda.findViewById(R.id.descripcion);
        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagen);

        JSONObject elemento=this.getItem(position);
        try {
            titulo.setText(elemento.getString("name"));
            subtitulo.setText(elemento.getString("use"));
            descripcion.setText(elemento.getString("activity"));
            String imagen=elemento.getString("id");
            int img= Integer.parseInt(imagen);
            String url = "https://www.kiva.org/img/510/"+img+".jpg";
            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;

    }

}
