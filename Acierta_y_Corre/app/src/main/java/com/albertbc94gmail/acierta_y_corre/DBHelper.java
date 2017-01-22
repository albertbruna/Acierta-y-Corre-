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

        db.execSQL("CREATE TABLE scores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "correctas INTEGER NOT NULL," +
                "fallidas INTEGER NOT NULL," +
                "porcentaje INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS preguntas");
        //db.execSQL("DROP TABLE IF EXISTS testErrores");
        db.execSQL("DROP TABLE IF EXISTS puntuaciones");
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

    public void eliminarErrores(int[] pregBien) {
        int preg;
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < pregBien.length; i++) {
            preg = pregBien[i];
            db.delete("testErrores","id = ?", new String[] {String.valueOf(preg)});
        }
        db.close();
    }

    public Test generarTest() {
        Test t = new Test();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM preguntas ORDER BY RANDOM() LIMIT 30" , null);
        int i = 0,j=0;
        if (c.moveToFirst()) {
            do {
                String[] distractors = {c.getString(4), c.getString(5)};
                t.addQuestion(new Question(c.getInt(0), c.getString(2), distractors, c.getString(3), c.getString(6)));
                Log.d("generarTest", t.getQuestions().get(i++).toString());
                j++;
            } while (c.moveToNext());
        }
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

    public void add(int correctas, int fallidas) {
        int porcentaje = Math.round(((float) correctas / (correctas + fallidas)) * 100);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("correctas", Integer.toString(correctas));
        values.put("fallidas", Integer.toString(fallidas));
        values.put("porcentaje", Integer.toString(porcentaje));
        db.insert("scores", null, values);
        Log.d("Afegir Historial", Integer.toString(correctas));
        db.close();
    }

    public int getCount() {
        int count;

        SQLiteDatabase db = this.getReadableDatabase();
        count = db.rawQuery("SELECT id FROM scores", null).getCount();
        db.close();

        return count;
    }

    public Score get(int i) {
        Score score;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM scores", null);
        c.moveToFirst();
        for(int n = 0; n < i; n++) {
            c.moveToNext();
        }
        score = new Score(
                Integer.parseInt(c.getString(1)),
                Integer.parseInt(c.getString(2)),
                Integer.parseInt(c.getString(3)));
        db.close();

        return score;
    }

    public ArrayList<Score> getAll() {
        ArrayList<Score> score = new ArrayList<Score>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM scores", null);
        if(c.moveToFirst()) {
            do {
                score.add(new Score(
                        Integer.parseInt(c.getString(1)),
                        Integer.parseInt(c.getString(2)),
                        Integer.parseInt(c.getString(3))));
            } while (c.moveToNext());
        }
        db.close();

        return score;
    }
}