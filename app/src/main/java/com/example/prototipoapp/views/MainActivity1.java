package com.example.prototipoapp.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.prototipoapp.R;
import com.example.prototipoapp.adapter.ViewPageradapter;
import com.example.prototipoapp.databinding.ActivityMain1Binding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity1 extends AppCompatActivity {
    ActivityMain1Binding activityMain1Binding;
    private Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMain1Binding = ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(activityMain1Binding.getRoot());

        //richiamato l'adattatore della viewpage e collegato ad esso
        setUpViewPager();

        // settaggio dell'item home come principale
        Home();

        //collegato gli item della scheda xml "nav_item" ai rispettivi fragment medinate switch e adattati alla viewpage
        fragmentSwitch();

    }

    private void Home() {

        String inputExtra;
        inputExtra = getIntent().getStringExtra("key0");
        if (!(inputExtra == null)) {
            activityMain1Binding.viewPager.setCurrentItem(0);
            activityMain1Binding.chipNavigation.setItemSelected(R.id.info, true);
            inputExtra = null;
        } else {
            activityMain1Binding.viewPager.setCurrentItem(1);
            activityMain1Binding.chipNavigation.setItemSelected(R.id.home, true);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        }


    }

    private void fragmentSwitch() {

        activityMain1Binding.chipNavigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {


                switch (i) {


                    case R.id.info:
                        fragment = new SchedeFragment();
                        activityMain1Binding.viewPager.setCurrentItem(0);
                        break;

                    case R.id.home:
                        fragment = new HomeFragment();
                        activityMain1Binding.viewPager.setCurrentItem(1);
                        break;


                    case R.id.chat:
                        fragment = new ChatFragment();
                        activityMain1Binding.viewPager.setCurrentItem(2);
                        break;

                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        });
    }


    private void setUpViewPager() {
        ViewPageradapter viewPageradapter = new ViewPageradapter(getSupportFragmentManager(), FragmentStatePagerAdapter.
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        activityMain1Binding.viewPager.setAdapter(viewPageradapter);

        activityMain1Binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {


                    case 0:
                        activityMain1Binding.chipNavigation.setItemSelected(R.id.info, true);
                        break;
                    case 1:
                        activityMain1Binding.chipNavigation.setItemSelected(R.id.home, true);
                        break;


                    case 2:
                        activityMain1Binding.chipNavigation.setItemSelected(R.id.chat, true);

                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}