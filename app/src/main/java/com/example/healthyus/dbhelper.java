package com.example.healthyus;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class dbhelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "HealthyUs";
    final static int DATABASE_VERSION = 2;
    final static String TABLE1_NAME = "UserStatus";
    final static String TABLE2_NAME = "Address";
    final static String TABLE3_NAME = "Users";
    final static String TABLE4_NAME ="ConsumedFood";
    final static String TABLE5_NAME ="Location";
    final static String TABLE6_NAME ="Availability";
    final static String TABLE7_NAME ="AppointmentId";
    final static String TABLE8_NAME ="UserProblem";

    final static String T1COL_1 = "UserStatusId";
    final static String T1Col_2 = "isDoctor";
    final static String T1Col_3 = "isAdmin";
    final static String T1Col_4 = "isCashier";
    final static String T1Col_5 = "isPatient";

    final static String T2Col_1 = "AddressId";
    final static String T2Col_2 = "Streret";
    final static String T2Col_3 = "Province";
    final static String T2Col_4 = "City";
    final static String T2Col_5 = "PostalCode";

    final static String T3Col_1 = "UserId";
    final static String T3Col_2 = "FirstName";
    final static String T3Col_3 = "LastName";
    final static String T3Col_4 = "BirthDate";
    final static String T3Col_5 = "Phone";
    final static String T3Col_6 = "Password";
    final static String T3Col_7 = "Email";
    final static String T3Col_8 = "AddressId";
    final static String T3Col_9 = "UserStatusId";

    final static String T4Col_1 = "FoodId";
    final static String T4Col_2 = "Calories";
    final static String T4Col_3 = "FoodName";
    final static String T4Col_4 = "PortionSize";
    final static String T4Col_5 = "UserId";

    final static String T5Col_1 = "LocationId";
    final static String T5Col_2 = "PostalCode";
    final static String T5Col_3 = "AddressId";

    final static String T6Col_1 = "AvailabilityId";
    final static String T6Col_2 = "AvailabilityTime";
    final static String T6Col_3 = "UserId";
    final static String T6Col_4 = "LocationId";

    final static String T7Col_1 = "AppointmentId";
    final static String T7Col_2 = "AppointmentTime";
    final static String T7Col_3 = "AddressId";
    final static String T7Col_4 = "UserId";
    final static String T7Col_5 = "LocationId";

    final static String T8Col_1 = "UserProblemId";
    final static String T8Col_2 = "UserId";
    final static String T8Col_3 = "UserProblem";
    final static String T8Col_4 = "UserSolution";

    public dbhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String USquery = "CREATE TABLE " + TABLE1_NAME + " (" + T1COL_1 + " INTEGER PRIMARY KEY," +
                T1Col_2 + " INTEGER," + T1Col_3 + " INTEGER," + T1Col_4 + " INTEGER, " + T1Col_5 + " INTEGER)";

        db.execSQL(USquery);

        String aQuery = "CREATE TABLE " + TABLE2_NAME + " (" + T2Col_1 + " INTEGER PRIMARY KEY," +
                T2Col_2 + " TEXT," + T2Col_3 + " TEXT," + T2Col_4 + " TEXT, " + T2Col_5 + " TEXT)";
        db.execSQL(aQuery);

        String uQuery = "CREATE TABLE " + TABLE3_NAME + " (" + T3Col_1 + " INTEGER PRIMARY KEY," +
                T3Col_2 + " TEXT," + T3Col_3 + " TEXT," + T3Col_4 + " TEXT, " + T3Col_5 + " TEXT,"+
                T3Col_6 + " TEXT," + T3Col_7 + " TEXT," + T3Col_8 + " INTEGER, " + T3Col_9 + " INTEGER)";
        db.execSQL(uQuery);

        String cfQuery = "CREATE TABLE " + TABLE4_NAME + " (" + T4Col_1 + " INTEGER PRIMARY KEY," +
                T4Col_2 + " INTEGER," + T4Col_3 + " TEXT," + T4Col_4 + " INTEGER, " + T4Col_5 + " INTEGER)";
        db.execSQL(cfQuery);

        String lQuery = "CREATE TABLE " + TABLE5_NAME + " (" + T5Col_1 + " INTEGER PRIMARY KEY," +
                T5Col_2 + " TEXT," + T5Col_3 + " INTEGER)";
        db.execSQL(lQuery);

        String avQuery = "CREATE TABLE " + TABLE6_NAME + " (" + T6Col_1 + " INTEGER PRIMARY KEY," +
                T6Col_2 + " TEXT," + T6Col_3 + " INTEGER," + T6Col_4 + " INTEGER)";
        db.execSQL(avQuery);

        String appointmentQuery = "CREATE TABLE " + TABLE7_NAME + " (" + T7Col_1 + " INTEGER PRIMARY KEY," +
                T7Col_2 + " TEXT," + T7Col_3 + " INTEGER," + T7Col_4 + " INTEGER,"+ T7Col_5 + " INTEGER)";
        db.execSQL(appointmentQuery);

        String userProblemQuery = "CREATE TABLE " + TABLE8_NAME + " (" + T8Col_1 + " INTEGER PRIMARY KEY," +
                T8Col_2 + " INTEGER," + T8Col_3 + " TEXT," + T8Col_4 + " TEXT)";
        db.execSQL(userProblemQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists " + TABLE1_NAME);
        db.execSQL("DROP TABLE if exists " + TABLE2_NAME);
        db.execSQL("DROP TABLE if exists " + TABLE3_NAME);
        db.execSQL("DROP TABLE if exists " + TABLE4_NAME);
        db.execSQL("DROP TABLE if exists " + TABLE5_NAME);
        db.execSQL("DROP TABLE if exists " + TABLE6_NAME);
        db.execSQL("DROP TABLE if exists " + TABLE7_NAME);
        db.execSQL("DROP TABLE if exists " + TABLE8_NAME);
        onCreate(db);
    }
    public void signUp(SQLiteDatabase db, String fName, String lName, String password, String email, String phone, Date birthDate){
        String hashedpassword = String.valueOf(password.hashCode());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strBirth = dateFormat.format(birthDate);
        String query = "INSERT INTO Users (FirstName, LastName, BirthDate, Phone, Password, Email ) VALUES ("+ fName + "," + lName + "," + strBirth + "," + phone + "," + hashedpassword + "," + email + ");";
        db.execSQL(query);
    }

}
