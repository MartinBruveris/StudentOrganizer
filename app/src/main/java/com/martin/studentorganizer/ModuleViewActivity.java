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

public class ModuleViewActivity extends AppCompatActivity
{

    DbManager dbmanager;
    Cursor result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_module);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        dbmanager = new DbManager(this);
        dbmanager.open();
        result = dbmanager.getAllModules();
        String[] fromColumns = {DbManager.KEY_ID, DbManager.KEY_MODULE_TITLE, DbManager.KEY_LECTURER};
        int[] toViews = {R.id.module_id, R.id.module_title, R.id.lecturer};

        ModuleViewActivity.MyCursorAdapter dataAdapter = new ModuleViewActivity.MyCursorAdapter(this, R.layout.single_row_module, result, fromColumns, toViews, 0);

        ListView listView = (ListView) findViewById(R.id.module_list);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        dbmanager.close();
    }

    private class MyCursorAdapter extends SimpleCursorAdapter
    {

        public MyCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags)
        {
            super(context, layout, c, from, to, flags);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            //get reference to the row
            View view = super.getView(position, convertView, parent);
            //check for odd or even to set alternate colors to the row background
            if (position % 2 == 0)
            {
                view.setBackgroundColor(Color.rgb(238, 233, 233));
            } else
            {
                view.setBackgroundColor(Color.rgb(255, 255, 255));
            }
            return view;
        }
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
