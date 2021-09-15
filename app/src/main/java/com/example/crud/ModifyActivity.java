package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import Extension.LoadingClass;
import Model.User;

import static com.example.crud.MainActivity.addList;
import static com.example.crud.MainActivity.updateList;


public class ModifyActivity extends AppCompatActivity {
    private EditText name;
    private EditText age;
    private EditText address;
    private Button button;
    private TextView title;
    private Toolbar toolbar;
    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        button = findViewById(R.id.modify_button);
        name = findViewById(R.id.name_input);
        age = findViewById(R.id.age_input);
        address = findViewById(R.id.address_input);
        title = findViewById(R.id.modify_title);
        toolbar = findViewById(R.id.toolbarModify);

        //Check intent to identify with switch if get was add or edit
        Bundle bundle = getIntent().getExtras();
        String request = bundle.getString("SEND");
        int pos = bundle.getInt("pos");
        switch(request){
            //Continue with add
            case "add":
                //setText in this part only accepts int parameter, so we use String.valueOf
                title.setText(String.valueOf("Add User"));
                button.setText(String.valueOf("Save Data"));
                //Debug in Logcat
                Log.d("add","Success in add switch.");
                toolbarBackListener();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoadingClass load = new LoadingClass(ModifyActivity.this);
                        load.startLoad();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                load.dismissLoad();
                            }
                        }, 3000);
                        //Add into object then throw as parcelable
                        //Use variable initials, except ad = address to throw
                        String n = String.valueOf(name.getText().toString().trim());
                        String a = String.valueOf(age.getText().toString().trim());
                        String ad = String.valueOf(address.getText().toString().trim());

                        //Temporary object thrower
                        User uTemp = new User(n,a,ad);


                        addList(uTemp);
                        Intent back = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(back);
                    }
                });
                break;
            case "edit":
                title.setText(String.valueOf("Edit User"));
                button.setText(String.valueOf("Update Data"));
                //Debug in Logcat
                Log.d("edit","Success in edit switch.");
                toolbarBackListener();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoadingClass load = new LoadingClass(ModifyActivity.this);
                        load.startLoad();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                load.dismissLoad();
                            }
                        }, 3000);
                        //Add into object then throw as parcelable
                        //Use variable initials, except ad = address to throw
                        String nam = String.valueOf(name.getText().toString().trim());
                        String ag = String.valueOf(age.getText().toString().trim());
                        String add = String.valueOf(address.getText().toString().trim());

                        //Temporary object thrower
                        User uT = new User(nam,ag,add);
                        Log.d("posCheck",String.valueOf(pos));
                        Intent editIntent = new Intent(getBaseContext(), MainActivity.class);

                        //Make data as intent

                        updateList(pos, uT);
                        startActivity(editIntent);

                    }
                });
                break;
            default:
                //Error Handling
                break;
        }

    }
    //Toolbar Navigation Back
    private void toolbarBackListener(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Debug
                Log.d("back","Back Button Working");
            }
        });
    }
}