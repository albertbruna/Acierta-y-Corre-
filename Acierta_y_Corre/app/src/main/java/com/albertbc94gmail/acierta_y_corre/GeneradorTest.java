package com.albertbc94gmail.acierta_y_corre;

/**
 * Created by Albert on 13/01/2017.
 */

public class GeneradorTest {
    public static Test generarTest(){
        Test t = new Test();
        String[] distractors = {"a","b"};
        String correctAnswer = "c";
        t.addQuestion(new Question("question",distractors, correctAnswer, null));
        return t;
    }
}
