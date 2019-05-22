package com.lnysky.tech.scorpio.sample;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnysky.tech.scorpio.Scorpio;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class Test2Fragment extends Fragment {

    public Test2Fragment() {
        // Required empty public constructor
    }

    public static Test2Fragment newInstance() {
        Test2Fragment fragment = new Test2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test2, container, false);
        return Scorpio.with(this).wrapper(view);
    }

    public void show(int state) {
        switch (state) {
            case 1:
                loading();
                break;
            case 2:
                empty();
                break;
            case 3:
                error();
                break;
            case 4:
                content();
                break;
        }
    }

    private void content() {
        Scorpio.with(this).content().show();
    }

    private void loading() {
        Scorpio.with(this).loading().setTips("加载中...").show();
    }

    private void empty() {
        Scorpio.with(this).empty().setTips("主页面空空的~~").show();
    }

    private void error() {
        Scorpio.with(this)
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

}
