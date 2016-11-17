package com.example.pam.expendablelistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ardy on 11/12/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1; //Database Version
    private static final String DATABASE_NAME = "db_person"; //Database Name
    private static final String TABLE_PERSON = "tb_person"; //Table Name

    //Table Columns Names
    private static final String KEY_ID = "id_person";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_HOBBY = "hobby";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PERSON_TABLE = "CREATE TABLE " + TABLE_PERSON + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_HOBBY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_PERSON); //Drop older table if existed
        onCreate(sqLiteDatabase); //Creating tables again
    }

    //Function Create Data
    public void addPerson(Person person){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, person.getName()); //Person Name
        contentValues.put(KEY_ADDRESS, person.getAddress()); //Person Address
        contentValues.put(KEY_HOBBY, person.getHobby()); //Person Hobby

        sqLiteDatabase.insert(TABLE_PERSON, null, contentValues); //Inserting Row
        sqLiteDatabase.close(); //Closing database connection
    }

    //Function Read Data
    public List<Person> getAllPerson(){
        List<Person> personList = new ArrayList<Person>();
        String selectQuery = "SELECT * FROM " + TABLE_PERSON; //Select All Query
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        //Looping all rows and add it to list
        if (cursor.moveToFirst()){
            do {
                Person person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0))); //Get Id Column
                person.setName(cursor.getString(1)); //Get Name Column
                person.setAddress(cursor.getString(2)); //Get Address Column
                person.setHobby(cursor.getString(3)); //Get Hobby Column
                personList.add(person); //Add Person Object to Person List
            } while (cursor.moveToNext());
        }
        return personList; //Return Person List
    }
}
