package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lny on 2018/11/28.
 */
public final class Content extends State {

    private View view;

    public Content(StateSwitcher switcher, View view) {
        super(switcher);
        this.view = view;
    }

    @Override
    public StateViewHolder onCreateStateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new StateViewHolder(view);
    }

}
