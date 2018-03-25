package com.example.ubay.achmadirjikubay_1202154184_modul5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    public DbHelper dbHelper;
    private RecyclerView mRecyclerView;
    private ArrayList<List> mModel;
    private Adapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper=new DbHelper(this);
        mModel = new ArrayList<>();


        mRecyclerView = (RecyclerView)findViewById(R.id.recylerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new Adapter (this,mModel);//menghubungkan adapter layout
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,input_item.class);
                startActivity(intent);
            }
        });
        intitialiseData();
        //helper ketika di swap
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
           //method ketika recycler view pindah
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                Collections.swap(mModel, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }
            //ketika diswap
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                com.example.ubay.achmadirjikubay_1202154184_modul5.List listModel = mModel.get(viewHolder.getAdapterPosition());
                String id = String.valueOf(listModel.getID());

                mModel.remove(viewHolder.getAdapterPosition());
                //remove data
                dbHelper.deleteItem(listModel.getID());

                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        //megabungkan helper ke recycler view
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,settingactivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void intitialiseData(){
        Cursor c = dbHelper.getAllData();
        mModel.clear();
        while(c.moveToNext()){
            mModel.add(new List(c.getInt(0), c.getString(1), c.getString(2),c.getString(3)));
        }

        mAdapter.notifyDataSetChanged();

    }
    }



