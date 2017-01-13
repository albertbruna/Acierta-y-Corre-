package com.albertbc94gmail.acierta_y_corre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert on 13/01/2017.
 */

public class Test {

    private int Id;

    private List<Question> questions;

    public Test() {
        this.questions = new ArrayList<Question>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public int getId() {
        return Id;
    }
}
