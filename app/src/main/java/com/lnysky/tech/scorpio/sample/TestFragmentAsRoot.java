package com.lnysky.tech.scorpio.sample;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lnysky.tech.scorpio.Scorpio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class TestFragmentAsRoot extends Fragment {

    public TestFragmentAsRoot() {
        // Required empty public constructor
    }

    public static TestFragmentAsRoot newInstance() {
        TestFragmentAsRoot fragment = new TestFragmentAsRoot();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_as_root, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (isVisible()) {
            inflater.inflate(R.menu.state, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_loading:
                loading();
                break;
            case R.id.action_empty:
                empty();
                break;
            case R.id.action_error:
                error();
                break;
            case R.id.action_custom:
                custom();
                return true;
            case R.id.action_content:
                content();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void content() {
        Scorpio.with(this)
                .content()
                .show();
    }

    private void loading() {
        Scorpio.with(this)
                .loading()
                .setTips("加载中...")
                .show();
    }

    private void empty() {
        Scorpio.with(this)
                .empty()
                .setTips("主页面空空的~~")
                .show();
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

    private void custom() {
        Scorpio.with(this)
                .get(CustomState.class)
                .show();
    }

}
