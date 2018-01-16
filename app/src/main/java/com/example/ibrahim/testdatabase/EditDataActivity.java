package com.example.ibrahim.testdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditDataActivity extends AppCompatActivity {

    Button btnSave, btnDelete;
    EditText editable_item;
    DataBaseHelper mDBHelper;
    String selectedName;
    int selectedID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        mDBHelper = new DataBaseHelper(this);

        //get the intent extras from the ListDataActivity
        Intent receivedIntent = getIntent();
        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1);
        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        //set the text to show the current selected name
        editable_item.setText(selectedName);

        //Event sur le bouton SAVE
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDBHelper.updateName(item,selectedID,selectedName);
                }else{
                    Toast.makeText(getApplicationContext(),"You must enter a name",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Event sur le bouton DELETE
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDBHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                Toast.makeText(getApplicationContext(),"removed from database",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
