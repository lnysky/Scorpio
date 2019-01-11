package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lny on 2018/11/28.
 */
public class Empty extends Provider {

    private ImageView emptyImage;
    private TextView emptyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.scorpio_state_empty, parent, false);
        emptyImage = view.findViewById(R.id.empty_image);
        emptyText = view.findViewById(R.id.empty_text);
        return view;
    }

    public Empty setImg(int img) {
        if (emptyImage != null) {
            emptyImage.setImageResource(img);
        }
        return this;
    }

    public Empty setTips(String tips) {
        if (emptyText != null) {
            emptyText.setText(tips);
        }
        return this;
    }

}
