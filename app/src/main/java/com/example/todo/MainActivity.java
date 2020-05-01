package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private dbManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { DatabaseHelper.TASK_NAME,
            DatabaseHelper.TASK_DESCRIPTION, DatabaseHelper.TASK_STATUS };

    final int[] to = new int[]{ R.id.taskname, R.id.taskdescription, R.id.taskstatus };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new dbManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.view_task, cursor, from, to, 1);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
//                TextView idTextView = (TextView) view.findViewById(R.id.tasknumber);
                TextView titleTextView = (TextView) view.findViewById(R.id.taskname);
                TextView descTextView = (TextView) view.findViewById(R.id.taskdescription);
                TextView statusTextView = (TextView) view.findViewById(R.id.taskstatus);
                Log.e("viewid", String.valueOf(viewId));

//                String tasknumber = idTextView.getText().toString();
                String taskname = titleTextView.getText().toString();
                String taskdescription = descTextView.getText().toString();
                String taskstatus = statusTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyTask.class);
//                modify_intent.putExtra("id", tasknumber);
                modify_intent.putExtra("taskname", taskname);
                modify_intent.putExtra("description", taskdescription);
                modify_intent.putExtra("status",taskstatus);
                modify_intent.putExtra("taskid",String.valueOf(viewId));

                startActivity(modify_intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_task:
                newtask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void newtask()
    {
        Intent intent = new Intent(this, adding_task.class);
        startActivity(intent);
    }
}
