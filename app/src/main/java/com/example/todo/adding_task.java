package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adding_task extends AppCompatActivity implements View.OnClickListener {

    private Button addTodoBtn;
    private EditText taskEditText;
    private EditText descriptionEditText;
    private EditText statusEditText;

    private dbManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_task);
        taskEditText=(EditText) findViewById(R.id.taskEditText);
        descriptionEditText=(EditText) findViewById(R.id.descriptionEditText);
        statusEditText = (EditText) findViewById(R.id.statusEditText);
        addTodoBtn = (Button) findViewById(R.id.createButton);
        dbManager = new dbManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createButton:

                final String taskname = taskEditText.getText().toString();
                final String taskdescription = descriptionEditText.getText().toString();
                final String taskstatus = statusEditText.getText().toString();

                dbManager.insert(taskname, taskdescription, taskstatus);

                Intent main = new Intent(adding_task.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}
