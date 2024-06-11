package com.example.uassalmansqlite.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.uassalmansqlite.model.Students;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbmahasiswa";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STD = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_NIM = "nim";
    private static final String KEY_NAME = "nama";
    private static final String KEY_HP = "hp";
    private static final String KEY_STUDI = "studi";
    private static final String KEY_EMAIL = "email";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_STD + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT, "
            + KEY_NIM + " INTEGER, " + KEY_HP + " INTEGER," + KEY_STUDI + " TEXT," + KEY_EMAIL + " TEXT );";
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STD + "'");
        onCreate(db);
    }

    public void store(String nim, String name, String hp, String studi, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NIM, nim);
        values.put(KEY_NAME, name);
        values.put(KEY_HP, hp);
        values.put(KEY_STUDI, studi);
        values.put(KEY_EMAIL, email);

        db.insert(TABLE_STD, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<Students> getAllStudents() {
        ArrayList<Students> studentsList = new ArrayList<Students>();

        String query = "SELECT * FROM " + TABLE_STD;
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Students studens = new Students();
                studens.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                studens.setNim(c.getString(c.getColumnIndex(KEY_NIM)));
                studens.setNama(c.getString(c.getColumnIndex(KEY_NAME)));
                studens.setHp(c.getString(c.getColumnIndex(KEY_HP)));
                studens.setStudi(c.getString(c.getColumnIndex(KEY_STUDI)));
                studens.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));

                studentsList.add(studens);
            } while (c.moveToNext());
        }

        return studentsList;
    }

    public int update(int id, String nim, String name, String hp, String studi, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NIM, nim);
        values.put(KEY_NAME, name);
        values.put(KEY_HP, hp);
        values.put(KEY_STUDI, studi);
        values.put(KEY_EMAIL, email);

        return db.update(TABLE_STD, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STD, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
