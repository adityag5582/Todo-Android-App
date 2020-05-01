package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyTask extends AppCompatActivity implements View.OnClickListener {
    private EditText tasknameText;
    private Button updateBtn, deleteBtn;
    private EditText descriptionText;
    private EditText statusText;
    private EditText idText;

    private long _id;

    private dbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_task);

        dbManager = new dbManager(this);
        dbManager.open();

        tasknameText = (EditText) findViewById(R.id.taskname_edittext);
        descriptionText = (EditText) findViewById(R.id.description_edittext);
        statusText = (EditText) findViewById(R.id.status_edittext);
//        idText = (EditText) findViewById(R.id.id_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
//        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("taskname");
        String desc = intent.getStringExtra("description");
        String status = intent.getStringExtra("status");
        String taskid = intent.getStringExtra("taskid");

        _id = Long.parseLong(taskid);


//        idText.setText(id);
        tasknameText.setText(name);
        descriptionText.setText(desc);
        statusText.setText(status);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
//                String id=idText.getText().toString();
                String title = tasknameText.getText().toString();
                String desc = descriptionText.getText().toString();
                String status = statusText.getText().toString();

                dbManager.update(_id, title, desc,status);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
