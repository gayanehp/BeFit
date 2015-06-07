package com.example.gayaneh.befit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by Gayaneh on 6/1/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;

    private String[] projection={
            Contract.FoodEntry._ID,
            Contract.FoodEntry.BRAND,
            Contract.FoodEntry.BREAKFAST,
            Contract.FoodEntry.CALORIES,
            Contract.FoodEntry.DINNER,
            Contract.FoodEntry.ITEM,
            Contract.FoodEntry.LUNCH,
            Contract.FoodEntry.TYPE,
            Contract.FoodEntry.UNIT,

    };

    private static final String DATABASE_CREATE =
            "CREATE TABLE " +
                    Contract.FoodEntry.TABLE_NAME + " (" +
                    Contract.FoodEntry._ID + " INTEGER PRIMARY KEY, " +
                    Contract.FoodEntry.ITEM + " TEXT NOT NULL, " +
                    Contract.FoodEntry.BRAND + " TEXT NOT NULL, " +
                    Contract.FoodEntry.TYPE + " INTEGER NOT NULL, " +
                    Contract.FoodEntry.UNIT + " INTEGER NOT NULL, " +
                    Contract.FoodEntry.BREAKFAST + " BOOLEAN NOT NULL, " +
                    Contract.FoodEntry.LUNCH + " BOOLEAN NOT NULL, " +
                    Contract.FoodEntry.DINNER + " BOOLEAN NOT NULL, " +
                    Contract.FoodEntry.CALORIES + " INTEGER NOT NULL " + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.FoodEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("My Code: ", "Create table command: " + DATABASE_CREATE);

        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void insertFoodEntry(BeFitFood food) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Contract.FoodEntry._ID, food.getId());
        cv.put(Contract.FoodEntry.ITEM, food.getItem());
        cv.put(Contract.FoodEntry.BRAND, food.getBrand());
        cv.put(Contract.FoodEntry.TYPE, food.getType());
        cv.put(Contract.FoodEntry.UNIT, food.getUpc());
        cv.put(Contract.FoodEntry.CALORIES, food.getCalories());
        cv.put(Contract.FoodEntry.BREAKFAST, food.getIsBreakfast() ? 1 : 0);
        cv.put(Contract.FoodEntry.LUNCH, food.getIsLunch() ? 1 : 0);
        cv.put(Contract.FoodEntry.DINNER, food.getIsDinner() ? 1 : 0);

        db.insert(Contract.FoodEntry.TABLE_NAME, null, cv);
    }

    public Cursor getAllRows() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Contract.FoodEntry.TABLE_NAME, projection, null, null, null, null, null);

//        Here's the method with arguments:
//        public Cursor query (String table, String[] columns, String selection, String[]
//        selectionArgs, String groupBy, String orderBy, String limit)

    }

    public Cursor getRowByID(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] ids = {String.valueOf(id)};
        return db.query(Contract.FoodEntry.TABLE_NAME, projection, Contract.FoodEntry._ID + "==?", ids, null, null, null);
    }

//
//    public void addRows(List<FlickrPhoto> photos) {
//        for (FlickrPhoto photo : photos) {
//            insertPhotoEntry(photo);
//        }
//    }

    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + Contract.FoodEntry.TABLE_NAME);
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }


}
