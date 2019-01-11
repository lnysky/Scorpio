package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lny on 2018/11/28.
 */
public class Error extends Provider {

    private ImageView errorImage;
    private TextView errorText;
    private TextView retryButton;
    private View.OnClickListener onRetryListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.scorpio_state_error, parent, false);
        errorImage = view.findViewById(R.id.error_image);
        errorText = view.findViewById(R.id.error_text);
        retryButton = view.findViewById(R.id.retry_button);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRetryListener != null) {
                    onRetryListener.onClick(v);
                }
            }
        });
        return view;
    }

    public Error setImg(int img) {
        if (errorImage != null) {
            errorImage.setImageResource(img);
        }
        return this;
    }

    public Error setTips(String tips) {
        if (errorText != null) {
            errorText.setText(tips);
        }
        return this;
    }

    public Error setRetryText(String retryText) {
        if (retryButton != null) {
            retryButton.setText(retryText);
        }
        return this;
    }

    public Error setOnRetryListener(View.OnClickListener onRetryListener) {
        this.onRetryListener = onRetryListener;
        return this;
    }

}
