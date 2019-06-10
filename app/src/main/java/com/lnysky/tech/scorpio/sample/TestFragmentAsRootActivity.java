package com.lnysky.tech.scorpio.sample;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
