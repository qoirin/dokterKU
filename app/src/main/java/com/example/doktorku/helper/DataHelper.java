package com.example.doktorku.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bookingdokter.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table pembooking (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table dokter(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table booking(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references dokter (merk), " +
                "foreign key(nama) references pembooking (nama) " +
                ");" +
                "");

        db.execSQL("insert into dokter values (" +
                "'Dokter Umum'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into dokter values (" +
                "'Dokter Kandungan'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into dokter values (" +
                "'Dokter Mata'," +
                "150000" +
                ");" +
                "");
        db.execSQL("insert into dokter values (" +
                "'Dokter Jantung'," +
                "250000" +
                ");" +
                "");
        db.execSQL("insert into dokter values (" +
                "'Dokter THT'," +
                "200000" +
                ");" +
                "");
        db.execSQL("insert into dokter values (" +
                "'Dokter Penyakit Dalam'," +
                "400000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from dokter";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
