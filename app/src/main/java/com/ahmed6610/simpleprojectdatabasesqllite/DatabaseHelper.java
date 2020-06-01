package com.ahmed6610.simpleprojectdatabasesqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahmed6610.simpleprojectdatabasesqllite.business.PersonBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper  extends SQLiteOpenHelper{

    private  static  final int DB_VERSION=1;
    private  static  final String DB_NAME="UserInfo";
    private  static  final String TABLE_NAME="tbl_user";
    private  static  final String KEY_NAME="name";
    private  static  final String KEY_campny="campny";
    private  static  final String KEY_AGE="age";
    private  static  final String KEY_Phone="phone";

    private  static  final String TABLE_NAME1="tbl_user1";
    private  static  final String KEY_NAME1="name1";
    private  static  final String KEY_campny1="campny1";
    private  static  final String KEY_AGE1="age1";
    private  static  final String KEY_Phone1="phone1";
    private  static  final String KEY_Time="time";


    private  static  final String TABLE_NAME2="tbl_user2";
    public  static final String KEY_ID1 = "id";
    private  static  final String KEY_NAME2="name2";
    private  static  final String KEY_Castmer="castmer";
    private  static  final String KEY_campny2="campny2";
    private  static  final String KEY_AGE2="age2";
    private  static  final String KEY_Phone2="phone2";
    private  static  final String KEY_Time1="time1";




    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable="Create Table "+TABLE_NAME+"("+KEY_NAME+" TEXT PRIMARY KEY,"+KEY_campny+" TEXT,"+KEY_AGE+" TEXT  ,"+KEY_Phone +" TEXT "+ ")";
        db.execSQL(createUserTable);

        String createUserTable1="Create Table "+TABLE_NAME1+"("+KEY_NAME1+" TEXT PRIMARY KEY,"+KEY_campny1+" TEXT,"+KEY_AGE1+" TEXT  ,"+KEY_Phone1 +" TEXT ,"+KEY_Time+" TEXT "+ ")";
        db.execSQL(createUserTable1);

        String createUserTable2="Create Table "+TABLE_NAME2+"("+KEY_ID1+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+KEY_NAME2+" TEXT ,"+KEY_Castmer+" TEXT,"+KEY_campny2+" TEXT,"+KEY_AGE2+" TEXT  ,"+KEY_Phone2 +" TEXT ,"+KEY_Time1+" TEXT "+ ")";
        db.execSQL(createUserTable2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME1");
        db.execSQL("DROP TABLE IF EXISTS  TABLE_NAME2");

        onCreate(db);
    }

    public void  insert(PersonBean personBean){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,personBean.getName());
        values.put(KEY_campny,personBean.getCampny());
        values.put(KEY_AGE,personBean.getAge());
        values.put(KEY_Phone,personBean.getPhone());

        db.insert(TABLE_NAME,null,values);
    }





    public List<PersonBean> selectUserData(){
        ArrayList<PersonBean> userList=new ArrayList<PersonBean>();

        SQLiteDatabase db= getReadableDatabase();
        String[] columns={KEY_NAME,KEY_campny,KEY_AGE,KEY_Phone};

        Cursor c =db.query(TABLE_NAME,columns,null,null,null,null,null);

        while (c.moveToNext()){
            String name=c.getString(0);
            String campny=c.getString(1);
            String age=c.getString(2);
            String phone=c.getString(3);

            PersonBean personBean=new PersonBean();
            personBean.setName(name);
            personBean.setCampny(campny);
            personBean.setAge(age);
            personBean.setPhone(phone);
            userList.add(personBean);
        }

        return  userList;
    }





    public  void  delete(String name){
        SQLiteDatabase db =getWritableDatabase();
        String whereClause=KEY_NAME+"='"+name+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }
    public  void  delete_Accounts(String name){
        SQLiteDatabase db =getWritableDatabase();
        String whereClause=KEY_NAME1+"='"+name+"'";
        db.delete(TABLE_NAME1,whereClause,null);
    }

    public void update(PersonBean personBean){
        SQLiteDatabase db=getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_AGE,personBean.getAge());
        values.put(KEY_Phone,personBean.getPhone());
        values.put(KEY_campny,personBean.getCampny());
        String whereClause=KEY_NAME+"='"+personBean.getName()+"'";
        db.update(TABLE_NAME,values,whereClause,null);
    }




    // Get User Details//// العملاء
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name,campny ,age,phone  FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("campny",cursor.getString(cursor.getColumnIndex(KEY_campny)));
            user.put("Phone",cursor.getString(cursor.getColumnIndex(KEY_AGE)));
            user.put("Email",cursor.getString(cursor.getColumnIndex(KEY_Phone)));
            userList.add(user);
        }
        return  userList;
    }


    // Get User Details//// الحسابات
    public ArrayList<HashMap<String, String>> Accounts_GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name1,campny1 ,age1,phone1,time  FROM "+ TABLE_NAME1;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME1)));
            user.put("campny",cursor.getString(cursor.getColumnIndex(KEY_campny1)));
            user.put("Phone",cursor.getString(cursor.getColumnIndex(KEY_AGE1)));
            user.put("Email",cursor.getString(cursor.getColumnIndex(KEY_Phone1)));
            user.put("Time",cursor.getString(cursor.getColumnIndex(KEY_Time)));
            userList.add(user);
        }
        return  userList;
    }

    // Get User Details//// المخازن
    public ArrayList<HashMap<String, String>> Story_GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name1,campny1 ,time  FROM "+ TABLE_NAME1;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME1)));
            user.put("campny",cursor.getString(cursor.getColumnIndex(KEY_campny1)));
            user.put("Time",cursor.getString(cursor.getColumnIndex(KEY_Time)));
            userList.add(user);
        }
        return  userList;
    }


    // Get User Details//// التقارير
    public ArrayList<HashMap<String, String>> import_GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name2,castmer,campny2 ,age2,phone2,time1  FROM "+ TABLE_NAME2;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name2",cursor.getString(cursor.getColumnIndex(KEY_NAME2)));
            user.put("castmer",cursor.getString(cursor.getColumnIndex(KEY_Castmer)));
            user.put("campny2",cursor.getString(cursor.getColumnIndex(KEY_campny2)));
            user.put("age2",cursor.getString(cursor.getColumnIndex(KEY_AGE2)));
            user.put("phone2",cursor.getString(cursor.getColumnIndex(KEY_Phone2)));
            user.put("time1",cursor.getString(cursor.getColumnIndex(KEY_Time1)));
            userList.add(user);
        }
        return  userList;
    }





}
