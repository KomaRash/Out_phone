package com.example.user_pc.project_trtpo.SupportClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user_pc.project_trtpo.R;
import com.example.user_pc.project_trtpo.Schedules.Schedule;

import java.util.Deque;

public class SqlHelper extends SQLiteOpenHelper{
    private static String NameDB="ScheduleTrtpo";
    public static String TableSchedule="Schedule";
    public  static String TableInfo="Info";
    public  static String TableStats="Stats";
    public  static String DayOftheWeek="DayWeek";
    public  static String Date="date";
    public  static String TimeUse="useTime";
    public  static String Fio="fio";
    public  static String Auditory="Auditory";
    public  static String lessonTime="lessonTime";
    public  static String Subject="Subject";
    public  static final  int DB_V=1;
    public static String lessonType="lessonType";
    public static String SubGroup="SubGroup";
    public static String Week="Week";

    public SqlHelper(Context context)
    {
        super(context,NameDB,null,DB_V);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Schedule( _id INTEGER Primary Key Autoincrement,"+DayOftheWeek+" TEXT,"+Subject+
                " TEXT,"+Auditory+" TEXT,"+lessonTime+" TEXT,"+
                lessonType+" TEXT,"+Fio+" TEXT,"+SubGroup+" TEXT,"+Week+" TEXT)");
        db.execSQL("CREATE TABLE "+TableStats+"(_id INTEGER Primary key Autoincrement,"+DayOftheWeek+" TEXT,"+Date+" TEXT,"+TimeUse+" time)");
        db.execSQL("CREATE TABLE "+TableInfo+"(Name TEXT,Numgroup TEXT,idPets INTEGER,NamePets TEXT)");

    }
    public static void InsertINDB(SQLiteDatabase db, String nameTable, ContentValues contentValues)
    {
        db.insert(nameTable,null,contentValues);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }



}
