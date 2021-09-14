package com.example.crud;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.ArrayList;

import Adapter.UserAdapter;
import Model.User;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private UserAdapter adapter;
    private static ArrayList<User> userList = new ArrayList<User>();;
    private RecyclerView recyclerView;
    private TextView emptyView;
    private MaterialToolbar toolbar;

//    public static ArrayList<User> userList = new ArrayList<>();

    //non private
    ActivityResultLauncher<Intent> activityResultLauncher;


    //Using Method for userListArray
    public static void addList(User user){
        userList.add(user);
    }
    public static void updateList(int indx, User user){
        userList.set(indx, user);
    }
    public static void removeList(int indx){
        userList.remove(indx);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.floating_button);
        recyclerView = findViewById(R.id.list_recycler);
        adapter = new UserAdapter(userList, MainActivity.this);
        toolbar = findViewById(R.id.toolbar);


        emptyView = findViewById(R.id.empty_view);
        //New startActivityForResult as it is deprecated (intentLaunch)
        //Edit: Does not work in 3 activities.
        Intent checkIntent = getIntent();

        checkEmptyData();
        initRecyclerView();
        changeActivityListener();


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == 101 && resultCode == 111 && data != null) {
//
//            //Debug
//            Log.d("debug", "Add");
//
//            userList.add(data.getParcelableExtra("user_data"));
//
//            //Dummy Edit
////            User dum = new User("Hahaha","8","Hahaha");
////            userList.set(0, dum);
//            checkEmptyData();
//            adapter.notifyDataSetChanged();
//        } else if (requestCode == 212 && resultCode == 222 && data != null) {
//            //Use Bundle
//            Bundle bundle = getIntent().getExtras();
//
//            //Debug
//            Log.d("debug", "Edit");
//            Log.d("data_pos",String.valueOf(data.getIntExtra("data_pos",-1)));
//            userList.set(data.getIntExtra("data_pos", -1), data.getParcelableExtra("data_user"));
//        } else if (requestCode == 323 && resultCode == 333 && data != null) {
//
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    //Listener for setOnClick for onCreate/main function
    private void changeActivityListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ModifyActivity.class);

                //Sending condition through other class by the variable SEND
                String cond = "add";
                i.putExtra("SEND", cond);
//                startActivityForResult(i, 0); - deprecated line
                startActivity(i);

            }
        });
    }
    //Dummy Data
    private void addDummyData(){
//        userList.add(new User("Agus", "8", "Surakarta"));
//        userList.add(new User("Arman", "9", "Suramadu"));
//        userList.add(new User("Wijyanto", "10","Jakarta"));
//        adapter.notifyDataSetChanged();
    }
    //Check if data is empty
    private void checkEmptyData(){
        if (userList.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            emptyView.setVisibility(View.GONE);
            initRecyclerView();
        }
    }
    //RecyclerView initiation
    private void initRecyclerView(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}