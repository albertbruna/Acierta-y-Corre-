package com.albertbc94gmail.acierta_y_corre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Francisco on 21/01/2017.
 */

public class ActivityNumTema2 extends AppCompatActivity {
    private Button back,finalizartest;
    private Test test;
    private ListView listViewTest;
    private TestAdapter adapter;
    private DBHelper db = DBHelper.getInstance(this);
    private int[] respuestasMalas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        test = db.generarTestTemas("2");
        listViewTest = (ListView) findViewById(R.id.listViewtest);


        adapter = new TestAdapter(this,
                R.layout.activity_pregunta,
                R.id.textViewQuestion,
                R.id.imageViewQuestion,
                R.id.radiogroup_answers,
                new int[]{R.id.radioButton,R.id.radioButton2,R.id.radioButton3},
                R.id.check,test.getQuestions(),
                test.isFinalized()
        );

        listViewTest.setAdapter(adapter);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        finalizartest = (Button)findViewById(R.id.buttonFinalizar);
        finalizartest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setFinalized(true);
                adapter.setFinalized(true);
                adapter.notifyDataSetChanged();
                ShowMessage();
                respuestasMalas = test.respuestasmal();
                db.añadirErrores(respuestasMalas);
                db.add(test.countCorrectAnswers(),test.countWrongAnswers());
            }
        });

    }

    public void ShowMessage(){
        String succesMessage = getResources().getString(R.string.num_succes)+test.countCorrectAnswers();
        String errorsMessage = getResources().getString(R.string.num_errors)+test.countWrongAnswers();
        String message = succesMessage + "\n" + errorsMessage;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titolDialeg);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.create().show();
    }



}
