package com.albertbc94gmail.acierteycorreapp;

/**
 * Created by Albert on 02/12/2016.
 */

public class GeneradorTest {
    public static Test generarTest() {
        Test t = new Test();
        String[] distractors = {"a","b","c"};
        t.addQuestion(new Question("question",distractors, "resposta", null));

        return t;
    }
}
