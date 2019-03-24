package com.martin.studentorganizer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class UpcomingTaskActivity extends AppCompatActivity
{
    DbManager dbmanager;
    Cursor result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_task);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        dbmanager = new DbManager(this);
        dbmanager.open();
        result = dbmanager.getAllTasks();
        String[] fromColumns = {DbManager.KEY_MODULEID, DbManager.KEY_MODULE_TITLE, DbManager.KEY_TASK_TYPE, DbManager.KEY_DUE_DATE};


        int[] toViews = {R.id.module_id, R.id.module_title, R.id.task_type, R.id.due_date};

        MyCursorAdapter dataAdapter = new MyCursorAdapter(this, R.layout.single_row, result, fromColumns, toViews, 0);

        ListView listView = (ListView) findViewById(R.id.list);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        dbmanager.close();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_home:
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            }

            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
