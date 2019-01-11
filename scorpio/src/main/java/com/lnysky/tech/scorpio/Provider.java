package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lny on 2018/11/28.
 */
public abstract class Provider {

    private StateLayout.Holder holder;

    void setHolder(StateLayout.Holder holder) {
        this.holder = holder;
    }

    StateLayout.Holder getHolder() {
        return holder;
    }

    public void show() {
        holder.show();
    }

    public View getView() {
        return holder.getView();
    }

    protected void onDisplay(boolean display) {

    }

    public abstract View onCreateView(LayoutInflater inflater, ViewGroup parent);

}
