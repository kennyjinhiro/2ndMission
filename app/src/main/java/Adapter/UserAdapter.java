package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.DetailActivity;
import com.example.crud.MainActivity;
import com.example.crud.ModifyActivity;
import com.example.crud.R;

import java.util.ArrayList;

import Model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> userList;
    private Context context;

    public UserAdapter(ArrayList<User> userList, Context context){this.userList = userList; this.context = context;}
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Compile XML file into View files
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.name.setText(String.valueOf(userList.get(position).getFullName()));
        holder.age.setText(String.valueOf(userList.get(position).getAge()));
        holder.address.setText(String.valueOf(userList.get(position).getAddress()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent Card
                Intent iCard = new Intent(context, DetailActivity.class);

                //Sending position through other class by the variable POSITION
                iCard.putExtra("user_detail",userList.get(position));
                iCard.putExtra("pos", position);
                Log.d("POSNUMBER",String.valueOf("Current Index " + position));
//                startActivityForResult(i, 0); - deprecated line
                context.startActivity(iCard);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name, age, address;
        private ImageView prof_pic;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameText);
            age = itemView.findViewById(R.id.ageText);
            address = itemView.findViewById(R.id.addressText);
            prof_pic = itemView.findViewById(R.id.profilePicture);
        }
    }
}
