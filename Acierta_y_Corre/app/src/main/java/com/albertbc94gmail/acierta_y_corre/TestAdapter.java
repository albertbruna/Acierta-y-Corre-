package com.albertbc94gmail.acierta_y_corre;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

/**
 * Created by Albert on 13/01/2017.
 */

public class TestAdapter extends ArrayAdapter<Question> {
    private final int buttonGroup;
    private final int imageViewQuestion;
    private final int imageViewVerify;
    private int[] radioButtons;
    //private int textViewResourceId;

    //private int textViewResourceId;
    public TestAdapter(Context context, int layout, int textViewQuestion,  int imageViewQuestion, int buttonGroup, int[] radioButtons, int imageViewVerify, List<Question> objects) {
        super(context, layout, textViewQuestion, objects);
        this.buttonGroup = buttonGroup;
        this.imageViewQuestion =imageViewQuestion;
        this.imageViewVerify = imageViewVerify;
        this.radioButtons = radioButtons;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = super.getView(position, convertView, parent);

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
        if(q.getResposta()!=-1) {
            rg.check(radioButtons[q.getResposta()]);
        }
        else {
            rg.check(q.getResposta());
        }
        for(int i=0; i<q.getRespostes().length; i++) {
            RadioButton rb = (RadioButton)rg.getChildAt(i);
            rb.setText(q.getRespostes()[i]);
            final int answer = i;
            final int question = position;
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickAnswer(question,answer);
                }
            });
        }
        return convertView;
    }

    //este metodo es para q guarde la respuesta clicada, sino al mover la lista o girar la pantalla se perderia lo seleccionado
    protected void clickAnswer(int question, int answer) {
        Question q = getItem(question);
        if(q.getResposta()==answer) answer = -1;
        q.setResposta(answer);
        notifyDataSetChanged();
    }
}
