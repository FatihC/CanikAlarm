package com.caniksoft.canikalarm;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.caniksoft.canikalarm.entity.AlarmEntity;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CanikAlarm.db";
    private static final String ALARM_TABLE = "Alarm";

    private static DBHelper instance;
    public static DBHelper get() {
        return instance;
    }

    public static DBHelper get(Context context) {
        if (instance == null) {
            instance = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        return instance;
    }



    private DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, DATABASE_VERSION);
    }

    private DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, DATABASE_VERSION, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private DBHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, DATABASE_VERSION, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAlarmTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertAlarm(AlarmEntity alarm) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME", alarm.getName());
        values.put("HOUR", alarm.getHour());
        values.put("MINUTE", alarm.getMinute());
        values.put("DAYS", alarm.getDaysString());

        long newRowId = db.insert(ALARM_TABLE, null, values);

    }

    private boolean deleteAlarm(Integer serno) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ALARM_TABLE, "SERNO" + " = ?", new String[] {String.valueOf(serno)}) > 0;
    }

    public List<AlarmEntity> getAlarmList() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from " + ALARM_TABLE, new String[] {});

        List<AlarmEntity> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            AlarmEntity alarm = new AlarmEntity();
            alarm.setSerno(cursor.getInt(cursor.getColumnIndex("SERNO")));
            alarm.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            alarm.setHour(cursor.getInt(cursor.getColumnIndex("HOUR")));
            alarm.setMinute(cursor.getInt(cursor.getColumnIndex("MINUTE")));
            alarm.setDays(cursor.getString(cursor.getColumnIndex("DAYS")));

            list.add(alarm);
        }

        return list;
    }

    private void createAlarmTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + ALARM_TABLE);

        String createTableSql = "CREATE TABLE " + ALARM_TABLE + " (" +
                "SERNO INTEGER PRIMARY KEY," +
                "NAME TEXT, " +
                "HOUR INTEGER, " +
                "MINUTE INTEGER," +
                "DAYS TEXT)";
        db.execSQL(createTableSql);
    }




}
