package com.martin.studentorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ModuleSubViewActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_subview);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button viewModules = (Button)findViewById(R.id.view_modules_button);
        viewModules.setOnClickListener(this);
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

    public void onClick(View view)
    {
        Intent intent = new Intent();
        if(view == findViewById(R.id.view_modules_button))
        {
            intent = new Intent(this, ModuleViewActivity.class);
        }
        startActivity(intent);
    }
}
