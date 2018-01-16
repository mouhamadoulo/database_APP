package com.example.ibrahim.testdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListDataActivity extends AppCompatActivity {

    DataBaseHelper mDBHelper;
    ListView mListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDBHelper = new DataBaseHelper(this);

        populateListView();
    }

    //method for displaying data in the ListView
    private void populateListView(){
        //get the data and append it to a list
        Cursor data = mDBHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        final ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                //get the id associated with the name
                Cursor data = mDBHelper.getItemID(name);
                int itemID = -1;
                while (data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Intent i  = new Intent(ListDataActivity.this,EditDataActivity.class);
                    i.putExtra("id",itemID);
                    i.putExtra("name",name);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"No ID associated with that name",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
