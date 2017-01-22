package com.albertbc94gmail.acierta_y_corre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Albert on 22/01/2017.
 */

public class ResultsAdapter extends BaseAdapter {
    private DBHelper db; // Base de datos
    private LayoutInflater inflador;
    TextView nAciertos, nFallos, id;
    ProgressBar porcentaje;

    public ResultsAdapter(Context context) {
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        db = DBHelper.getInstance(context);
    }

    public View getView(int i, View vistaReciclada, ViewGroup padre) {
        Score puntuacion = db.get(i);
        if (vistaReciclada == null) {
            vistaReciclada = inflador.inflate(R.layout.result, null);
        }
        nAciertos = (TextView) vistaReciclada.findViewById(R.id.listNAciertos);
        nFallos = (TextView) vistaReciclada.findViewById(R.id.listNFallos);
        porcentaje = (ProgressBar) vistaReciclada.findViewById(R.id.listPorcentaje);

        nAciertos.setText(Integer.toString(puntuacion.getCorrectas()));
        nFallos.setText(Integer.toString(puntuacion.getFallidas()));
        porcentaje.setProgress(puntuacion.getPorcentaje());

        return vistaReciclada;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
