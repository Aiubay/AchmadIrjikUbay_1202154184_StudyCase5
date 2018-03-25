package com.example.ubay.achmadirjikubay_1202154184_modul5;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class input_item extends AppCompatActivity {
    public DbHelper dbHelper;
    private EditText mJudul,mDesc,mPriority;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_item);
        //reference variable
        mJudul = (EditText) findViewById(R.id.inputTitle);
        mDesc = (EditText) findViewById(R.id.inputDsc);
        mPriority = (EditText) findViewById(R.id.inputPriority);
        mButton = (Button) findViewById(R.id.btnInput);
        addData();
    }
    public void addData(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper = new DbHelper(input_item.this);
                String name = mJudul.getText().toString();//get pada textview
                String desc = mDesc.getText().toString();//get pada text view
                int priority = Integer.parseInt(mPriority.getText().toString());// get pada textview
                //dbHelper.InsertData(name,desc,priority;
                boolean isInserted = dbHelper.insertNewItem(name,desc,priority);
                if (isInserted){
                    Toast.makeText(input_item.this,"Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(input_item.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(input_item.this,"Input Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
