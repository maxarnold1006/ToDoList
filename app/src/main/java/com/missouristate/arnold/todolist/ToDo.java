package com.missouristate.arnold.todolist;

public class ToDo {
    private int id;
    private String task;


    public ToDo(int newId, String newTask){
        setId( newId );
        setTask( newTask );

    }

    public void setId( int newId ) {
        id = newId;
    }

    public void setTask( String newTask ) {
        task = newTask;
    }




    public int getId( ) {
        return id;
    }

    public String getTask( ) {
        return task;
    }

    public String toString( ) {
        return id + "; " + task;
    }
}