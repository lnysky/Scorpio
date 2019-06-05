package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

/**
 * Created by lny on 2018/11/28.
 */
public class Error extends State<Error.ViewHolder> {

    private View.OnClickListener onRetryListener;
    private String tips;
    private String retryText;
    @DrawableRes
    private int errorImage;

    @Override
    protected ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.scorpio_state_error, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onSwitchState(ViewHolder holder, boolean showing) {
        super.onSwitchState(holder, showing);
        if (errorImage != 0) {
            holder.errorImage.setImageResource(errorImage);
        }
        holder.errorText.setText(tips);
        holder.retryButton.setText(retryText);
    }

    public Error setImg(int img) {
        this.errorImage = img;
        return this;
    }

    public Error setTips(String tips) {
        this.tips = tips;
        return this;
    }

    public Error setRetryText(String retryText) {
        this.retryText = retryText;
        return this;
    }

    public Error setOnRetryListener(View.OnClickListener onRetryListener) {
        this.onRetryListener = onRetryListener;
        return this;
    }

    class ViewHolder extends StateLayout.ViewHolder {

        ImageView errorImage;
        TextView errorText;
        TextView retryButton;

        ViewHolder(View view) {
            super(view);
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
        }
    }

}
