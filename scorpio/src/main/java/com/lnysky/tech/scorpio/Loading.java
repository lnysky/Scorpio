package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lny on 2018/11/28.
 */
public class Loading extends Provider {

    private TextView loadingText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.scorpio_state_loading, parent, false);
        loadingText = view.findViewById(R.id.loading_text);
        return view;
    }

    public Loading setTips(String tips) {
        if (loadingText != null) {
            loadingText.setText(tips);
        }
        return this;
    }
}
