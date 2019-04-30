package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lny on 2018/11/28.
 */
public abstract class State<VH extends StateViewHolder> {

    private StateSwitcher switcher;

    void setSwitcher(StateSwitcher switcher) {
        this.switcher = switcher;
    }

    public void show() {
        switcher.switchState(this);
    }

    protected abstract VH onCreateStateViewHolder(LayoutInflater inflater, ViewGroup parent);

    protected void onSwitchState(VH holder, boolean show) {
        holder.getView().setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
