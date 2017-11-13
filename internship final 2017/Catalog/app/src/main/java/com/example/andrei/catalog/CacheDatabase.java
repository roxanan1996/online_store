package com.example.andrei.catalog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Andrei on 7/4/2017.
 */

public class CacheDatabase extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "catalog";

    // Table Names
    private static final String TABLE_NAME = "thumbnails";

    // column names
    private static final String KEY_ID = "id";
    private static final String KEY_THUMBNAIL = "thumbnail";

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_NAME + "("+
            KEY_ID + " TEXT," +
            KEY_THUMBNAIL + " BLOB);";

    public CacheDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table
        db.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // create new table
        onCreate(db);
    }

    public void insert(String id, Bitmap bitmap) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] image = stream.toByteArray();

        ContentValues cv = new  ContentValues();
        cv.put(KEY_ID,    id);
        cv.put(KEY_THUMBNAIL,   image);
        database.insert( TABLE_NAME, null, cv );
    }

    public Bitmap get(String id) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_NAME, new String[] { KEY_THUMBNAIL }, KEY_ID + "=?",
                new String[] { id }, null, null, null, null);
        if (cursor.getCount() < 1)
            return null;
        else
            cursor.moveToFirst();

        byte[] bitmapdata = cursor.getBlob(0);

        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
        return bitmap;
    }
}


