package com.albertbc94gmail.acierteycorreapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert on 02/12/2016.
 */

public class Test {
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
}
