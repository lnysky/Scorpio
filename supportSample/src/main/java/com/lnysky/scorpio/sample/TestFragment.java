package com.lnysky.scorpio.sample;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.lnysky.scorpio.Scorpio;


public class TestFragment extends Fragment {

    public TestFragment() {
        // Required empty public constructor
    }

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test, container, false);
        return Scorpio.with(this).wrapper(view);
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
