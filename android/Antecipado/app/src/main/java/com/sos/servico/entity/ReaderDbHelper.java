package com.sos.servico.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by deivison on 5/18/15.
 */
public class ReaderDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ReaderContract.AvaliacaoEntry.TABLE_NAME + " (" +
                    ReaderContract.AvaliacaoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ReaderContract.AvaliacaoEntry.COLUMN_SERVICOID + TEXT_TYPE + COMMA_SEP +
                    ReaderContract.AvaliacaoEntry.COLUMN_SERVICOCATEGORIA + TEXT_TYPE + COMMA_SEP +
                    ReaderContract.AvaliacaoEntry.COLUMN_SERVICONAME + " UNIQUE" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ReaderContract.AvaliacaoEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "FeedReader.db";

    public ReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public int getAvaliacoes() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(*) as total from " + ReaderContract.AvaliacaoEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("total"));
            cursor.close();
            db.close();
            return total;
        }
        return 0;
    }

    public void deletaAvaliacao(String servicoId){
        SQLiteDatabase db = getWritableDatabase();
        String selection = ReaderContract.AvaliacaoEntry.COLUMN_SERVICOID + " = ?";
        String[] selectionArgs = { String.valueOf(servicoId) };
        db.delete(ReaderContract.AvaliacaoEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    public ReaderContract.AvaliacaoEntry[] listaAvaliacoes() {
        String[] projection = {
                ReaderContract.AvaliacaoEntry._ID,
                ReaderContract.AvaliacaoEntry.COLUMN_SERVICOID,
                ReaderContract.AvaliacaoEntry.COLUMN_SERVICONAME
        };

        String sortOrder = ReaderContract.AvaliacaoEntry._ID + " DESC";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.query(
                ReaderContract.AvaliacaoEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        int total = 0;
        Cursor cursor = db.rawQuery("select count(*) as total from " + ReaderContract.AvaliacaoEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        total = cursor.getInt(0);
        ReaderContract.AvaliacaoEntry[] retorno = new ReaderContract.AvaliacaoEntry[total];
        int i=0;
        while(c.moveToNext()){
            String name = c.getString( c.getColumnIndex(ReaderContract.AvaliacaoEntry.COLUMN_SERVICONAME) );
            String id = c.getString(c.getColumnIndex(ReaderContract.AvaliacaoEntry.COLUMN_SERVICOID) );
            ReaderContract.AvaliacaoEntry entity = new ReaderContract.AvaliacaoEntry();
            entity.setServicoId(id);
            entity.setServicoName(name);
            retorno[i] = entity;
            i +=1;
            Log.d("DB","listando =>"+name);
        }
        cursor.close();
        c.close();
        db.close();
        return retorno;
    }

    public void insertAvaliacao(String servicoid, String serviconame,String categoria) {
        try {

            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ReaderContract.AvaliacaoEntry.COLUMN_SERVICOID, servicoid);
            values.put(ReaderContract.AvaliacaoEntry.COLUMN_SERVICONAME, serviconame);
            values.put(ReaderContract.AvaliacaoEntry.COLUMN_SERVICOCATEGORIA, categoria);
            db.insert(
                    ReaderContract.AvaliacaoEntry.TABLE_NAME,
                    null,
                    values);
            db.close();
        }catch (Exception e){}
    }
}