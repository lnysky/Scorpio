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

import com.lnysky.tech.scorpio.Scorpio;
import com.lnysky.tech.scorpio.State;
import com.lnysky.tech.scorpio.StateSwitcher;
import com.lnysky.tech.scorpio.StateViewHolder;

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
                Scorpio.loading(this).setTips("加载中...").show();
                return true;
            case R.id.action_empty:
                Scorpio.empty(this).setTips("主页面空空的~~").show();
                return true;
            case R.id.action_error:
                Scorpio.error(this)
                        .setRetryText("重新加载")
                        .setOnRetryListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Scorpio.loading(MainActivity.this).show();
                            }
                        }).show();
                return true;
            case R.id.action_custom:
                Scorpio.with(this).get(CustomState.class).show();
                return true;
            case R.id.action_content:
                Scorpio.content(this).show();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.state, menu);
        return true;
    }

    public static class CustomState extends State<CustomState.ViewHolder> {

        public CustomState(StateSwitcher switcher) {
            super(switcher);
        }

        @Override
        protected ViewHolder onCreateStateViewHolder(LayoutInflater inflater, ViewGroup parent) {
            View view = inflater.inflate(R.layout.custom, parent, false);
            return new ViewHolder(view);
        }

        @Override
        protected void onSwitchState(ViewHolder holder, boolean show) {
            super.onSwitchState(holder, show);
            AlphaAnimation animation;
            if (show) {
                animation = new AlphaAnimation(0f, 1f);
            } else {
                animation = new AlphaAnimation(1f, 0f);
            }
            animation.setDuration(1000);
            holder.getView().startAnimation(animation);
        }

        static class ViewHolder extends StateViewHolder {

            ViewHolder(View view) {
                super(view);
            }
        }
    }

}
