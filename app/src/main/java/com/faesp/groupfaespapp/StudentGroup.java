package com.faesp.groupfaespapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class StudentGroup extends AppCompatActivity {

    ArrayList<Group> lGroup = new ArrayList<Group>();
    ListView listView;
    ListViewAdapter adapter;

    /* Teste */
    String raAluno = "5465785454";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_group);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Grupos");

        Button listarBtn = findViewById(R.id.listagroupsBtn);
        listarBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(StudentGroup.this, MainActivity.class);
                startActivity(intent) ;
            }
        });

        listView = findViewById(R.id.listView);

        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            } else {
                lGroup = GroupApi.findGroup("https://node-group-api.herokuapp.com/groups/student/"+raAluno);

                // passing modelGroup to viewAdapter
                adapter = new ListViewAdapter(this, lGroup);

                // passing adapter to listView
                listView.setAdapter(adapter);
            }
        }catch (JSONException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
