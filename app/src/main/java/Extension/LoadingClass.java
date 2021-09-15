package Extension;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import com.example.crud.R;

public class LoadingClass {
    //Call Activity and Alert Dialog
    private Activity activity;
    private AlertDialog dialog;

    public LoadingClass(Activity myActivity){
        activity = myActivity;
    }

    public void startLoad(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(200, 200);

    }

    public void dismissLoad(){
        dialog.dismiss();
    }
}
