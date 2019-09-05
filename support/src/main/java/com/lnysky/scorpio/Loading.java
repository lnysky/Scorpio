package com.lnysky.scorpio;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by lny on 2018/11/28.
 */
public class Loading extends StateLayout.State<Loading.ViewHolder> {

    private String tips;

    public Loading setTips(String tips) {
        this.tips = tips;
        return this;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.scorpio_state_loading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onSwitchState(ViewHolder holder, boolean showing) {
        super.onSwitchState(holder, showing);
        holder.loadingText.setText(tips);
    }

    static class ViewHolder extends StateLayout.ViewHolder {

        TextView loadingText;

        ViewHolder(View view) {
            super(view);
            loadingText = view.findViewById(R.id.loading_text);
        }
    }

}
