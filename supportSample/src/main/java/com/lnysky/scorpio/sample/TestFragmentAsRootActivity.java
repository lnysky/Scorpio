package com.lnysky.scorpio.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class TestFragmentAsRootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment_as_root);
        ViewPager viewPager = findViewById(R.id.view_pager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(TestFragmentAsRoot.newInstance());
        fragments.add(TestFragmentAsRoot.newInstance());
        fragments.add(TestFragmentAsRoot.newInstance());
        fragments.add(TestFragmentAsRoot.newInstance());
        fragments.add(TestFragmentAsRoot.newInstance());
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), fragments));
    }

    class MyAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments;

        MyAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
