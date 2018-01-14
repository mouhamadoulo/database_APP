package com.example.ibrahim.testdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper mDBHelper;
    Button btnAdd, btnViewData;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edt);
        btnAdd = (Button) findViewById(R.id.btnADD);
        btnViewData = (Button) findViewById(R.id.btnVIEW);
        mDBHelper = new DataBaseHelper(this);

        //Event sur le bouton ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length() != 0){
                    ajoutNom(newEntry);
                    editText.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"You must put something in the text field !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Event sur le bouton VIEW DATA
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListDataActivity.class);
                startActivity(i);
            }
        });
    }

    //méthode pour ajouter un nom dans notre base en faisant appel à notre DBHelper
    public void ajoutNom(String newEntry){
        boolean insertData = mDBHelper.addData(newEntry);
        if(insertData){
            Toast.makeText(this,"Data Successfully Inserted !",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
