package com.albertbc94gmail.acierta_y_corre;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.view.LayoutInflater;
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
    private int buttonGroup;
    private int imageViewQuestion;
    private int imageViewVerify;
    private int[] radioButtons;

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }

    private boolean finalized;

    //private int textViewResourceId;

    public TestAdapter(Context context, int layout, int textViewQuestion,  int imageViewQuestion, int buttonGroup, int[]radioButtons, int imageViewVerify, List<Question> objects, boolean finalized) {
        super(context, layout, textViewQuestion,objects);
        this.buttonGroup = buttonGroup;
        this.imageViewQuestion = imageViewQuestion;
        this.imageViewVerify = imageViewVerify;
        this.radioButtons = radioButtons;
        this.finalized = finalized;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = super.getView(position, convertView, parent);

        RadioGroup rg = (RadioGroup) convertView.findViewById(buttonGroup);
        ImageView imviewQuestion = (ImageView) convertView.findViewById(imageViewQuestion);
        ImageView imviewVerify = (ImageView) convertView.findViewById(imageViewVerify);

        Question q = this.getItem(position);

        if (!q.getRecurso().equals("null")) { // Si es modo pregunta con imagen se carga el recurso
            imviewQuestion.setImageResource(getImageId(getContext(), q.getRecurso()));
        }
        else imviewQuestion.setImageBitmap(null);

        if(isFinalized()) {
            if (q.getResposta() == -1 || !q.isCorrect()) {
                Bitmap bm = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.imgcheckwrong);
                imviewVerify.setImageBitmap(bm); //imatge a mostrar
            } else {
                Bitmap bm = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.imgcheckcorrect);
                imviewVerify.setImageBitmap(bm); //imatge correcte
            }
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
            if(!isFinalized()) {
                rb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickAnswer(question, answer);
                    }
                });
            }
            else {
                rb.setEnabled(false);
            }
        }
        return convertView;
    }

    protected void clickAnswer(int question, int answer) {
        Question q = getItem(question);
        if(q.getResposta()==answer) answer = -1;
        q.setResposta(answer);
        notifyDataSetChanged();
    }

    private int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
