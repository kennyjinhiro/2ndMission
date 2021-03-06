package com.example.crud;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Extension.LoadingClass;
import Model.User;

import static com.example.crud.MainActivity.removeList;


public class DetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView name;
    private TextView age;
    private TextView address;
    private FloatingActionButton edit;
    private FloatingActionButton delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = findViewById(R.id.detail_toolbar);
        name = findViewById(R.id.detail_name);
        age = findViewById(R.id.detail_age);
        address = findViewById(R.id.detail_address);
        edit = findViewById(R.id.edit_button);
        delete = findViewById(R.id.delete_button);

        //Check intent to identify with switch if get was add or edit
        Bundle bundle = getIntent().getExtras();
        User user = bundle.getParcelable("user_detail");
        User userEdit = bundle.getParcelable("data_user");
        int p = bundle.getInt("pos");

        name.setText(String.valueOf(user.getFullName()));
        age.setText(String.valueOf(user.getAge()));
        address.setText(String.valueOf(user.getAddress()));

        toolbarBackListener();
        editListener();
        deleteListener();
    }
    //Edit Listener
    private void editListener(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                User user = bundle.getParcelable("user_detail");
                int p = bundle.getInt("pos");

                Intent i = new Intent(DetailActivity.this, ModifyActivity.class);
                //Debug
                Log.d("pos",String.valueOf(p));
                //Sending condition through other class by the variable SEND
                String cond = "edit";
                i.putExtra("SEND", cond);
                i.putExtra("pos",p);
//                startActivityForResult(i, 0); - deprecated line
                startActivity(i);

//                String cond = "edit";
//                Intent editIntent = new Intent(DetailActivity.this, ModifyActivity.class);
//                editIntent.putExtra("SEND",cond);
//                editIntent.putExtra("POSITION",p);
//                startActivityForResult(editIntent,212);
            }
        });
    }
    //Delete Listener
    private void deleteListener(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                User user = bundle.getParcelable("user_detail");
                int p = bundle.getInt("pos");

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(DetailActivity.this);
                alertBuilder.setTitle("Delete");
                alertBuilder.setMessage("Are you sure you want to delete " + user.getFullName()+ "'s data?");
                alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoadingClass load = new LoadingClass(DetailActivity.this);
                        load.startLoad();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                load.dismissLoad();
                            }
                        }, 2000);
                        removeList(p);
                        Intent back = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(back);
                    }
                });
                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertBuilder.show();

            }
        });
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