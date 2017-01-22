//package com.albertbc94gmail.acierta_y_corre;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
///**
// * Created by Albert on 13/01/2017.
// */
//
//public class GeneradorTest {
//    private static int[] imageResourceIDs = {R.drawable.img2,
//            R.drawable.img4,
//            R.drawable.img6,
//            R.drawable.img8,
//            R.drawable.img10,
//            R.drawable.img12,
//            R.drawable.img14,
//            R.drawable.img16,
//            R.drawable.img18,
//            R.drawable.img21,
//            R.drawable.img23,
//            R.drawable.img25,
//            R.drawable.img28,
//            R.drawable.img30};
//
//    public static Test generarTest(Context context){
//        Test t = new Test();
//        for(int i=1; i<=14; i++) {
//            String[] distractors = {"op "+i+".1","op "+i+".2"};
//            String correctAnswer = "op "+i+".3";
//            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),imageResourceIDs[i-1]);
//            t.addQuestion(new Question("question "+i, distractors, correctAnswer, bm));
//        }
//
///*       t.addQuestion(new Question("Si, de noche, circula Ud. por una vía insuficientemente" +
//                " iluminada a más de 40 kilómetros por hora, ¿está obligado a " +
//                "llevar encendida la luz de largo alcance en su vehículo?.",
//                "Fuera de poblado, sí; dentro de poblado, no, porque está prohibido.;" +
//                        "Sí, tanto fuera como dentro de poblado y siempre que no esté obligado a utilizar la de cruce.;" +
//                        "Sí, pero únicamente en travesías y vías fuera de poblado.",null ));*/
//        return t;
//    }
//}
