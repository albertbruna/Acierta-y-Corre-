package com.albertbc94gmail.acierta_y_corre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Albert on 13/01/2017.
 */

public class ActivityTest extends AppCompatActivity {
    private Button back;

    private Test test;
    private ListView listViewTest;
    private TextView textViewQuestion;
    //private Question questions[];
    private ArrayAdapter<Question> adapter;
    private RadioButton[] radioButtonsAnswers;
    private int contOK;
    private boolean[] Verify;
    private String correctAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        test = GeneradorTest.generarTest();
        listViewTest = (ListView) findViewById(R.id.listViewtest);
        adapter = new TestAdapter(this,R.id.activity_pregunta,R.id.textViewQuestion,R.id.imageViewQuestion,R.id.radiogroup_answers,R.id.check,test.getQuestions());
        listViewTest.setAdapter(adapter);

//        radioButtonsAnswers = new RadioButton[3];
//        radioButtonsAnswers[0] = (RadioButton) findViewById(R.id.answer1);
//        radioButtonsAnswers[1] = (RadioButton) findViewById(R.id.answer2);
//        radioButtonsAnswers[2] = (RadioButton) findViewById(R.id.answer3);
//        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
