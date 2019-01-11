package com.lnysky.tech.scorpio.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(android.R.id.content, TestFragment.newInstance())
                .commitAllowingStateLoss();
    }
}
