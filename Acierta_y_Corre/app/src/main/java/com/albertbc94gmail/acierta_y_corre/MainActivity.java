package com.albertbc94gmail.acierta_y_corre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button EmpezarTest;
    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private Test test;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmpezarTest = (Button)findViewById(R.id.EmpezarTest);
        EmpezarTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent EmpezarTest = new Intent(MainActivity.this,ActivityTest.class);
                startActivity(EmpezarTest);

            }
        });

        imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imageButton1 = new Intent(MainActivity.this,ActivityTemas.class);
                startActivity(imageButton1);

            }
        });

        imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imageButton2 = new Intent(MainActivity.this,ActivityErrores.class);
                startActivity(imageButton2);

            }
        });


        imageButton3 = (ImageButton)findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imageButton3 = new Intent(MainActivity.this,ActivityHistorial.class);
                startActivity(imageButton3);

            }
        });

        //test = GeneradorTest.generarTest();
    }
}
