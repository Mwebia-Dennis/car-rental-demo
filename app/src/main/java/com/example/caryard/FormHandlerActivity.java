package com.example.caryard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
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
}