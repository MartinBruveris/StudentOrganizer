package com.martin.studentorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    DbManager dbmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        dbmanager = new DbManager(this);
        dbmanager.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        Button manageTasks = (Button)findViewById(R.id.manage_button);
        manageTasks.setOnClickListener(this);

        Button viewTasks = (Button)findViewById(R.id.view_button);
        viewTasks.setOnClickListener(this);

        Button takePhotoNote = (Button)findViewById(R.id.take_note_button);
        takePhotoNote.setOnClickListener(this);

        Button modules = (Button)findViewById(R.id.modules_button);
        modules.setOnClickListener(this);
    }


    public void onClick(View view)
    {
        Intent intent = new Intent();
        if(view == findViewById(R.id.manage_button))
        {
            intent = new Intent(this, ManageSubViewActivity.class);
        }

        if(view == findViewById(R.id.view_button))
        {
            intent = new Intent(this, TaskSubViewActivity.class);
        }

        if(view == findViewById(R.id.take_note_button))
        {
            intent = new Intent(this, PhotoNote.class);
        }

        if(view == findViewById(R.id.modules_button))
        {
            intent = new Intent(this, ModuleSubViewActivity.class);
        }
        startActivity(intent);
    }
}
