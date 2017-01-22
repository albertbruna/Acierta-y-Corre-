package com.albertbc94gmail.acierta_y_corre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Francisco on 16/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance = null;
    private Context context;
    private static final String DATABASE_NAME = "preguntas.db";
    private static final int DATABASE_VERSION = 6;

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE preguntas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tema VARCHAR(300) NOT NULL," +
                "pregunta VARCHAR(300) NOT NULL," +
                "respuesta_correcta VARCHAR(300) NOT NULL," +
                "respuesta_falsa_1 VARCHAR(300) NOT NULL," +
                "respuesta_falsa_2 VARCHAR(300) NOT NULL," +
                "recurso VARCHAR(300))");
        lecturaDeFicheroBD(db);

        db.execSQL("CREATE TABLE testErrores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idpreg INTEGER NOT NULL," +
                "pregunta VARCHAR(300) NOT NULL," +
                "respuesta_correcta VARCHAR(300) NOT NULL," +
                "respuesta_falsa_1 VARCHAR(300) NOT NULL," +
                "respuesta_falsa_2 VARCHAR(300) NOT NULL," +
                "recurso VARCHAR(300))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS preguntas");
        //db.execSQL("DROP TABLE IF EXISTS testErrores");
        onCreate(db);
    }

    private void lecturaDeFicheroBD(SQLiteDatabase sq) {
        String[] p;
        Scanner sc = new Scanner(this.context.getResources().openRawResource(R.raw.database));
        ContentValues values = new ContentValues();
        while (sc.hasNextLine()) {
            p = sc.nextLine().split(";");
            for(String s: p)
                Log.d("lecturaDeFicheroBD:",s);
            values.put("tema", p[0]);
            values.put("pregunta", p[1]);
            values.put("respuesta_correcta", p[2]);
            values.put("respuesta_falsa_1", p[3]);
            values.put("respuesta_falsa_2", p[4]);
            values.put("recurso", p[5]);
            sq.insert("preguntas",null,values);
           //this.getWritableDatabase().insert("preguntas", null, values);
        }
    }


    public void añadirErrores(int[] pregMalas) {
        int preg;
        SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase dbw = getWritableDatabase();
        for (int i = 0; i < pregMalas.length; i++) {
            preg = pregMalas[i];
            Cursor c = db.rawQuery("SELECT * FROM preguntas where id = " + preg, null);
            if (c.moveToFirst()) {
                do {
                    ContentValues values = new ContentValues();
                    //values.put("id", c.getInt(0));
                    values.put("idpreg", c.getString(0));
                    values.put("pregunta", c.getString(2));
                    values.put("respuesta_correcta", c.getString(3));
                    values.put("respuesta_falsa_1", c.getString(4));
                    values.put("respuesta_falsa_2", c.getString(5));
                    values.put("recurso", c.getString(6));
                    dbw.insert("testErrores", null, values);
                } while (c.moveToNext());
            }
            c.close();
        }
        dbw.close();
        db.close();
    }

    public Test generarTest() {
        Test t = new Test();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM preguntas", null);
        int i = 0;
        if (c.moveToFirst()) {
            do {
                String[] distractors = {c.getString(4), c.getString(5)};
                t.addQuestion(new Question(c.getInt(0), c.getString(2), distractors, c.getString(3), c.getString(6)));
                Log.d("generarTest", t.getQuestions().get(i++).toString());
            } while (c.moveToNext());
        }
        t.shuffle(); //Mezcla las preguntas
        db.close();
        return t;
    }

    public Test generarTestErrores() {
        Test t = new Test();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM testErrores", null);
        int i = 0;
        if (c.moveToFirst()) {
            do {
                String[] distractors = {c.getString(4), c.getString(5)};
                t.addQuestion(new Question(c.getInt(0), c.getString(2), distractors, c.getString(3), c.getString(6)));
                Log.d("generarTestErrores", t.getQuestions().get(i++).toString());
            } while (c.moveToNext());
        }
        t.shuffle(); //Mezcla las preguntas
        db.close();
        return t;
    }


    public Test generarTestTemas(String tema) {
        Test t = new Test();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM preguntas where tema= '" + tema + "'", null);
        int i = 0;
        if (c.moveToFirst()) {
            do {
                String[] distractors = {c.getString(4), c.getString(5)};
                t.addQuestion(new Question(c.getInt(0), c.getString(2), distractors, c.getString(3), c.getString(6)));
                Log.d("generarTest", t.getQuestions().get(i++).toString());
            } while (c.moveToNext());
        }
        t.shuffle(); //Mezcla las preguntas
        db.close();
        return t;
    }
}