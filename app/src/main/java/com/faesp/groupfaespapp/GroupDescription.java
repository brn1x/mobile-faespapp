package com.faesp.groupfaespapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class GroupDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_description);

        ActionBar actionBar = getSupportActionBar();

        // test
        final String raAluno = "5465785454";

        TextView groupType = findViewById(R.id.desc_groupType);
        TextView groupName = findViewById(R.id.desc_groupName);
        TextView groupDesc = findViewById(R.id.desc_groupDesc);
        TextView groupQtdAlunos = findViewById(R.id.qtdAlunos);
        Button cadastrarBtn = findViewById(R.id.cadastrarBtn);

        // getting data from the main page
        Intent intent = getIntent();
        String desc_PageName = intent.getStringExtra("actionBarTitle");
        final Integer desc_id = intent.getIntExtra("id",0);
        String desc_GroupName =  intent.getStringExtra("descGroupName");
        Integer desc_qtdMinP = intent.getIntExtra("descQtdMinP", 0);
        Integer desc_qtdMaxP = intent.getIntExtra("descQtdMaxP", 0);
        Integer desc_qtdEnc = intent.getIntExtra("descQtdEnc", 0);
        String desc_GroupType =  intent.getStringExtra("descGroupType");
        String desc_GroupDesc =  intent.getStringExtra("descGroupDesc");
        String desc_objGroup = intent.getStringExtra("descObjGroup");
        String desc_situacao = intent.getStringExtra("descSituacao");

//        Gson gson = new Gson();
//        Group descGroup = new Group(desc_id, desc_GroupName, desc_qtdMinP, desc_qtdMaxP, desc_qtdEnc, desc_GroupType, desc_GroupDesc, desc_objGroup, desc_situacao);
//        final String groupJson = gson.toJson(descGroup);

        cadastrarBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    GroupApi.requestPOST("https://node-group-api.herokuapp.com/groups/"+ desc_id +"/student", "{\"raStudent\":\""+ raAluno +"\"}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // setting data
        actionBar.setTitle(desc_PageName);
        groupType.setText(desc_GroupType);
        groupName.setText(desc_GroupName);
        groupDesc.setText(desc_GroupDesc);
        groupQtdAlunos.setText(""+desc_qtdMaxP);


        Group gr = new Group(desc_id, desc_GroupName, desc_qtdMinP, desc_qtdMaxP, desc_qtdEnc, desc_GroupType, desc_GroupDesc, desc_objGroup, desc_situacao);

    }
}
