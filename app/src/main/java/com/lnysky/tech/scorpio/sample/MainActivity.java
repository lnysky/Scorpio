package com.lnysky.tech.scorpio.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import com.lnysky.tech.scorpio.Provider;
import com.lnysky.tech.scorpio.Scorpio;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testFragment(View v) {
        startActivity(new Intent(this, TestActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_loading:
                Scorpio.with(this).loading().setTips("加载中...").show();
                return true;
            case R.id.action_empty:
                Scorpio.with(this).empty().setTips("主页面空空的~~").show();
                return true;
            case R.id.action_error:
                Scorpio.with(this).error()
                        .setRetryText("重新加载")
                        .setOnRetryListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Scorpio.with(MainActivity.this).loading().show();
                            }
                        }).show();
                return true;
            case R.id.action_custom:
                Scorpio.with(this).get(CustomProvider.class).show();
                return true;
            case R.id.action_content:
                Scorpio.with(this).content().show();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.state, menu);
        return true;
    }

    public static class CustomProvider extends Provider {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
            return inflater.inflate(R.layout.custom, parent, false);
        }

        @Override
        protected void onDisplay(boolean display) {
            super.onDisplay(display);
            AlphaAnimation animation;
            if (display) {
                animation = new AlphaAnimation(0f, 1f);
            } else {
                animation = new AlphaAnimation(1f, 0f);
            }
            animation.setDuration(1000);
            getView().startAnimation(animation);
        }
    }

}
