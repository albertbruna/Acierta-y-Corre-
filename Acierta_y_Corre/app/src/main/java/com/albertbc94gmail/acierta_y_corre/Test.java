package com.albertbc94gmail.acierta_y_corre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Albert on 13/01/2017.
 */

public class Test {

    private static List<Question> questions;
    //private DBHelper db;

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }

    private boolean finalized;

    public Test() {
        this.questions = new ArrayList<Question>();
        this.finalized = false;
    }
    public static void shuffle() {
        Collections.shuffle(questions);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public boolean isFinalized(){
        return finalized;
    }

    public int countCorrectAnswers() {
        int count = 0;
        for(Question q: getQuestions()) {
            if(q.isCorrect()) count++;
        }
        return count;
    }

    public int countWrongAnswers() {
        int count = 0;
        for(Question q: getQuestions()) {
            if(!q.isCorrect()) {
                count++;
            }
        }
        return count;
   }

    public int[] respuestasmal(){
        int i = 0;
        int[] pregMal;
        pregMal = new int[150];
        for(Question q: getQuestions()) {
            if (!q.isCorrect()) {
                pregMal[i] = q.getIdQuestion();
            }
            i++;
        }
        return pregMal;
    }
}
