package com.lnysky.tech.scorpio.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private Test2Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ViewPager viewPager = findViewById(R.id.view_pager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(TestFragment.newInstance());
        fragments.add(TestFragment.newInstance());
        fragments.add(TestFragment.newInstance());
        fragments.add(TestFragment.newInstance());
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(fragments.size());

        TextView tvLoading = findViewById(R.id.tv_loading);
        TextView tvEmpty = findViewById(R.id.tv_empty);
        TextView tvError = findViewById(R.id.tv_error);
        TextView tvContent = findViewById(R.id.tv_content);

        tvLoading.setOnClickListener(this);
        tvEmpty.setOnClickListener(this);
        tvError.setOnClickListener(this);
        tvContent.setOnClickListener(this);

        fragment2 = Test2Fragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.test2, fragment2)
                .commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_loading:
                fragment2.show(1);
                break;
            case R.id.tv_empty:
                fragment2.show(2);
                break;
            case R.id.tv_error:
                fragment2.show(3);
                break;
            case R.id.tv_content:
                fragment2.show(4);
                break;
        }
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
