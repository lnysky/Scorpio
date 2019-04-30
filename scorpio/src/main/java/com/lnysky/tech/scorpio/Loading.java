package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lny on 2018/11/28.
 */
public class Loading extends State<Loading.ViewHolder> {

    private String tips;

    public Loading setTips(String tips) {
        this.tips = tips;
        return this;
    }

    @Override
    protected ViewHolder onCreateStateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.scorpio_state_loading, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onSwitchState(ViewHolder holder, boolean show) {
        super.onSwitchState(holder, show);
        holder.loadingText.setText(tips);
    }

    static class ViewHolder extends StateViewHolder {

        TextView loadingText;

        ViewHolder(View view) {
            super(view);
            loadingText = view.findViewById(R.id.loading_text);
        }
    }

}
