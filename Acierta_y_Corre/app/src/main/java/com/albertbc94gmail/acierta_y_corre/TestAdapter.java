package com.albertbc94gmail.acierta_y_corre;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Albert on 13/01/2017.
 */

public class TestAdapter extends ArrayAdapter<Question> {
    private int buttonGroup;
    private int imageViewQuestion;
    private int imageViewVerify;
    private int[] radioButtons;

    //private int textViewResourceId;
    public TestAdapter(Context context, int layout, int textViewQuestion,  int imageViewQuestion, int buttonGroup, int imageViewVerify, List<Question> objects) {
        super(context, layout, textViewQuestion, objects);
        this.buttonGroup = buttonGroup;
        this.imageViewQuestion =imageViewQuestion;
        this.imageViewVerify = imageViewVerify;
        //this.radioButtons = radioButtons;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //test = getItem(position);
        View result = convertView;
        RadioGroup rg = (RadioGroup) convertView.findViewById(buttonGroup);
        ImageView imviewQuestion = (ImageView) convertView.findViewById(imageViewQuestion);
        ImageView imviewVerify = (ImageView) convertView.findViewById(imageViewVerify);

        Question q = this.getItem(position);

        imviewQuestion.setImageBitmap(q.getImage());
        if(q.getResposta()==-1 || !q.isCorrect()) {
            imviewVerify.setImageBitmap(null); //imatge a mostrar
        }
        else{
            imviewVerify.setImageBitmap(null); //imatge correcte
        }
        rg.check(q.getResposta());
        for(int i=0; i<q.getRespostes().length; i++) {
            ((RadioButton)rg.getChildAt(i)).setText(q.getRespostes()[i]);
        }

       // rg.setOnCheckedChangeListener();//quan canvi el seleccionat, modifica resposta

        return super.getView(position, convertView, parent);
    }
}
