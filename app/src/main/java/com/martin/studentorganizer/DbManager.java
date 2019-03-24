package com.martin.studentorganizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Martin B on 20/11/2016.
 */

public class DbManager
{
    public static final String KEY_ID = "_id";
    public static final String KEY_MODULEID = "module_id";
    public static final String KEY_MODULE_TITLE = "module_title";
    public static final String KEY_LECTURER = "lecturer";
    public static final String KEY_TASK_TYPE = "task_type";
    public static final String KEY_DUE_DATE = "due_date";
    private static final String DATABASE_NAME = "organizerDB";
    private static final String DATABASE_TABLE_TASK = "Task";
    private static final String DATABASE_TABLE_MODULE = "Module";
    private static final int DATABASE_VERSION = 1;

    // This is the string containing the SQL database create statement
    private static final String DATABASE_CREATE_MODULE = "create table " + DATABASE_TABLE_MODULE +" (_id text primary key, " +
            "module_title text not null, "+"lecturer text not null);";

    private static final String DATABASE_CREATE_TASK = "create table " + DATABASE_TABLE_TASK +" (_id integer primary key autoincrement, " +
            "module_id text not null, "+"module_title text not null, " + "task_type text not null,"+"due_date text not null," +
            "foreign key(module_id) references Module(_id), foreign key(module_title) references Module(module_title));";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DbManager(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(DATABASE_CREATE_MODULE);
            db.execSQL(DATABASE_CREATE_TASK);
            db.execSQL("insert into Module(_id, module_title, lecturer) values('MB303','SUPER MODULE TITLE','LECTURER SMARTPANTS');");
            db.execSQL("insert into Module(_id, module_title, lecturer) values('MB304','SUPER MODULE TITLE 2','LECTURER DUMBO');");
            db.execSQL("insert into Task(module_id, module_title, task_type, due_date) values('MB303','SUPER MODULE TITLE','homework','30-12-2016');");
            db.execSQL("insert into Task(module_id, module_title, task_type, due_date) values('MB303','SUPER MODULE TITLE','CA','12-12-2016');");
            db.execSQL("insert into Task(module_id, module_title, task_type, due_date) values('MB304','SUPER MODULE TITLE 2','test','28-12-2016');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }

    public DbManager open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    public long insertTask(String _id, String module_title, String task_type, String due_date)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_MODULEID, _id);
        initialValues.put(KEY_MODULE_TITLE, module_title);
        initialValues.put(KEY_TASK_TYPE, task_type);
        initialValues.put(KEY_DUE_DATE, due_date);
        return db.insert(DATABASE_TABLE_TASK, null, initialValues);
    }

    public long insertModule(String _id, String module_title, String lecturer)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_MODULEID, _id);
        initialValues.put(KEY_MODULE_TITLE, module_title);
        initialValues.put(KEY_LECTURER, lecturer);
        return db.insert(DATABASE_TABLE_MODULE, null, initialValues);
    }

    public Cursor getAllTasks()
    {
        return db.query(DATABASE_TABLE_TASK, new String[] {
                        KEY_ID,
                        KEY_MODULEID,
                        KEY_MODULE_TITLE,
                        KEY_TASK_TYPE,
                        KEY_DUE_DATE},
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getAllModules()
    {
        return db.query(DATABASE_TABLE_MODULE, new String[] {
                        KEY_ID,
                        KEY_MODULE_TITLE,
                        KEY_LECTURER},
                null,
                null,
                null,
                null,
                null);
    }
}
