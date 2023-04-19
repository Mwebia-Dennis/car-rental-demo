package com.example.caryard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import com.example.caryard.Adapters.ViewPagerAdapter;
import java.util.ArrayList;

public class FormHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_handler);

        // setting up viewpager, fragments and adapter to manager fragments
        ViewPager viewPager = findViewById(R.id.viewpager);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new CarDetailsFragment());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                fragmentList
        );
        viewPager.setAdapter((viewPagerAdapter));


    }

    @Override
    public void onBackPressed() {
        // show a dialog asking the user if they want to exit the app
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    // Exit the activity
                    dialog.dismiss();
                    FormHandlerActivity.this.finish();
                })
                .setNegativeButton("No", (dialog, id) -> {
                    // Dismiss the dialog and do nothing
                    dialog.cancel();
                    dialog.dismiss();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}