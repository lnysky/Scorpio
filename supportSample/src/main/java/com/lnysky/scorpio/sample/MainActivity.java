package com.lnysky.scorpio.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View v) {
        startActivity(new Intent(this, TestActivity.class));
    }

    public void testAsRoot(View v) {
        startActivity(new Intent(this, TestAsRootActivity.class));
    }

    public void testFragment(View v) {
        startActivity(new Intent(this, TestFragmentActivity.class));
    }

    public void testFragmentAsRoot(View v) {
        startActivity(new Intent(this, TestFragmentAsRootActivity.class));
    }

    public void testXml(View v) {
        startActivity(new Intent(this, XmlTestActivity.class));
    }

    public void testBestPractice(View v) {
        startActivity(new Intent(this, BestPracticeTestActivity.class));
    }

}
