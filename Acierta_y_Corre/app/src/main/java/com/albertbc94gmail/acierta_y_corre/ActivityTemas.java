package com.albertbc94gmail.acierta_y_corre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Albert on 13/01/2017.
 */

public class ActivityTemas extends AppCompatActivity {
    private Button back,buttonTema1,buttonTema2,buttonTema3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas);


        buttonTema1 = (Button)findViewById(R.id.buttonTema1);
        buttonTema1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent buttonTema1 = new Intent(ActivityTemas.this, ActivityNumTema.class);
                startActivity(buttonTema1);
                finish();
            }
        });

        buttonTema2 = (Button)findViewById(R.id.buttonTema2);
        buttonTema2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent buttonTema2 = new Intent(ActivityTemas.this, ActivityNumTema2.class);
                startActivity(buttonTema2);
                finish();
            }
        });

        buttonTema3 = (Button)findViewById(R.id.buttonTema3);
        buttonTema3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent buttonTema3 = new Intent(ActivityTemas.this, ActivityNumTema3.class);
                startActivity(buttonTema3);
                finish();
            }
        });



        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ActivityTemas.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });



    }
}