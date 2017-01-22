package com.albertbc94gmail.acierta_y_corre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Albert on 13/01/2017.
 */

public class ActivityHistorial extends AppCompatActivity {
    private Button back;
    private BaseAdapter adaptador;
    private ListView listViewHist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        adaptador = new ResultsAdapter(this);
        listViewHist = (ListView) findViewById(R.id.listViewHistorial);
        listViewHist.setAdapter(adaptador);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ActivityHistorial.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }
}
