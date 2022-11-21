package com.missouristate.arnold.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todoDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TODO = "ToDo";
    private static final String ID = "id";
    private static final String TODO = "todo";

    public DatabaseManager(Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_TODO + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + TODO;
        sqlCreate += " text )";

        db.execSQL( sqlCreate );
    }

    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_TODO );
        // Re-create tables
        onCreate( db );
    }

    public void insert( ToDo todo ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_TODO;
        sqlInsert += " values( null, '" + todo.getTask( );
        sqlInsert +=  "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }

    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_TODO;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }


    public ArrayList<ToDo> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<ToDo> tasks = new ArrayList<ToDo>( );
        while( cursor.moveToNext( ) ) {
            ToDo currentTask
                    = new ToDo( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ));
            tasks.add( currentTask );
        }
        db.close( );
        return tasks;
    }

    public ToDo selectById( int id ) {
        String sqlQuery = "select * from " + TABLE_TODO;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ToDo todo = null;
        if( cursor.moveToFirst( ) )
            todo = new ToDo( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ));
        return todo;
    }
}
