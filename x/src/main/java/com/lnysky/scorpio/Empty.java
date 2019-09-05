package com.lnysky.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

/**
 * Created by lny on 2018/11/28.
 */
public class Empty extends StateLayout.State<Empty.ViewHolder> {

    @DrawableRes
    private int img;
    private String tips;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.scorpio_state_empty, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onSwitchState(ViewHolder holder, boolean showing) {
        super.onSwitchState(holder, showing);
        if (img != 0) {
            holder.emptyImage.setImageResource(img);
        }
        holder.emptyText.setText(tips);
    }

    public Empty setImg(@DrawableRes int img) {
        this.img = img;
        return this;
    }

    public Empty setTips(String tips) {
        this.tips = tips;
        return this;
    }

    static class ViewHolder extends StateLayout.ViewHolder {

        ImageView emptyImage;
        TextView emptyText;

        ViewHolder(View view) {
            super(view);
            emptyImage = view.findViewById(R.id.empty_image);
            emptyText = view.findViewById(R.id.empty_text);
        }
    }

}
