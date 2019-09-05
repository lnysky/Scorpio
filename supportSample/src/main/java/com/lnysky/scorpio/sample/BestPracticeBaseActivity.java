package com.lnysky.scorpio.sample;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.lnysky.scorpio.Scorpio;
import com.lnysky.scorpio.StateLayout;

@SuppressLint("Registered")
public class BestPracticeBaseActivity extends AppCompatActivity {

    private StateLayout stateLayout;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_best_practice_base);
        stateLayout = findViewById(R.id.state_layout);
        View contentView = LayoutInflater.from(this)
                .inflate(layoutResID, stateLayout, false);
        stateLayout.setContentView(contentView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_loading:
                loading();
                return true;
            case R.id.action_empty:
                empty();
                return true;
            case R.id.action_error:
                error();
                return true;
            case R.id.action_custom:
                custom();
                return true;
            case R.id.action_content:
                content();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.state, menu);
        return true;
    }

    private void content() {
        Scorpio.with(stateLayout)
                .content()
                .show();
    }

    private void loading() {
        Scorpio.with(stateLayout)
                .loading()
                .setTips("加载中...")
                .show();
    }

    private void empty() {
        Scorpio.with(stateLayout)
                .empty()
                .setTips("主页面空空的~~")
                .show();
    }

    private void error() {
        Scorpio.with(stateLayout)
                .error()
                .setRetryText("重新加载")
                .setOnRetryListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loading();
                    }
                })
                .show();
    }

    private void custom() {
        Scorpio.with(stateLayout)
                .get(CustomState.class)
                .show();
    }

}
