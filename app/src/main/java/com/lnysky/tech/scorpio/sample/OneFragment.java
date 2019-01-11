package com.lnysky.tech.scorpio.sample;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lnysky.tech.scorpio.Scorpio;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneFragment extends Fragment implements View.OnClickListener {

    private View test;

    public OneFragment() {
        // Required empty public constructor
    }

    public static OneFragment newInstance() {
        OneFragment fragment = new OneFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        TextView tvLoading = view.findViewById(R.id.tv_loading);
        TextView tvEmpty = view.findViewById(R.id.tv_empty);
        TextView tvError = view.findViewById(R.id.tv_error);
        TextView tvContent = view.findViewById(R.id.tv_content);

        tvLoading.setOnClickListener(this);
        tvEmpty.setOnClickListener(this);
        tvError.setOnClickListener(this);
        tvContent.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        test = view.findViewById(R.id.tv_test);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_loading:
                Scorpio.with(test).loading().show();
                break;
            case R.id.tv_empty:
                Scorpio.with(test).empty().show();
                break;
            case R.id.tv_error:
                Scorpio.with(test).error().setOnRetryListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Scorpio.with(test).loading().show();
                    }
                }).show();
                break;
            case R.id.tv_content:
                Scorpio.with(test).content().show();
                break;
        }
    }
}
