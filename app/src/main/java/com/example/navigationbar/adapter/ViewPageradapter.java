package com.example.navigationbar.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.navigationbar.views.ChatFragment;
import com.example.navigationbar.views.HomeFragment;
import com.example.navigationbar.views.SchedeFragment;

public class ViewPageradapter extends FragmentStatePagerAdapter {

    public ViewPageradapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//il problema è qui la prima pagina deve essere home solo quando si avvia l'app
        switch (position) {


            case 0:
                return new SchedeFragment();

            case 1:
                return new HomeFragment();

            case 2:
                return new ChatFragment();

            default:
                return new HomeFragment();



    }
    }




    @Override
    public int getCount() {

        return 3;

    }

}
