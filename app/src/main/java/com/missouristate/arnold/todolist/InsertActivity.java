package com.missouristate.arnold.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager(this);
        setContentView( R.layout.activity_insert );
    }

    public void insert( View v ) {
        EditText taskEditText = findViewById( R.id.input_to_do );
        String taskString = taskEditText.getText( ).toString( );


        // insert new friend in database
        try {
            ToDo todo = new ToDo( 0, taskString);
            dbManager.insert( todo );
            Toast.makeText( this, "Task Added", Toast.LENGTH_SHORT ).show( );
        } catch (Exception e) {
            Toast.makeText( this, "Error", Toast.LENGTH_LONG ).show( );
        }


        // clear data
        taskEditText.setText( "" );
    }

    public void goBack( View v ) {
        this.finish( );
    }
}