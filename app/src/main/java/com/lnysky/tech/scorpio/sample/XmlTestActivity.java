package com.lnysky.tech.scorpio.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lnysky.tech.scorpio.Scorpio;
import com.lnysky.tech.scorpio.StateLayout;

import androidx.appcompat.app.AppCompatActivity;

public class XmlTestActivity extends AppCompatActivity implements View.OnClickListener {

    private StateLayout stateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_test);
        TextView tvContent = findViewById(R.id.tv_content);
        TextView tvLoading = findViewById(R.id.tv_loading);
        TextView tvEmpty = findViewById(R.id.tv_empty);
        TextView tvError = findViewById(R.id.tv_error);
        TextView tvCustom = findViewById(R.id.tv_custom);

        tvContent.setOnClickListener(this);
        tvLoading.setOnClickListener(this);
        tvEmpty.setOnClickListener(this);
        tvError.setOnClickListener(this);
        tvCustom.setOnClickListener(this);

        stateLayout = findViewById(R.id.state_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_content:
                content();
                break;
            case R.id.tv_loading:
                loading();
                break;
            case R.id.tv_empty:
                empty();
                break;
            case R.id.tv_error:
                error();
                break;
            case R.id.tv_custom:
                custom();
        }
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
