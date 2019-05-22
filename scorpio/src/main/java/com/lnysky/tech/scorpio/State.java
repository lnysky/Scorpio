package com.lnysky.tech.scorpio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * Created by lny on 2018/11/28.
 */
public abstract class State<VH extends StateLayout.ViewHolder> {

    StateSwitcher switcher;
    private VH viewHolder;

    VH createViewHolder(LayoutInflater inflater, ViewGroup parent) {
        if (viewHolder != null) {
            throw new IllegalStateException();
        }
        viewHolder = onCreateViewHolder(inflater, parent);
        return viewHolder;
    }

    VH getViewHolder() {
        return viewHolder;
    }

    public final void show() {
        if (switcher == null) throw new NullPointerException("must be set switcher");
        switcher.switchState(this);
    }

    void display(boolean show) {
        onSwitchState(viewHolder, show);
    }

    protected void onSwitchState(VH holder, boolean show) {
        holder.stateView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    protected abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);


    static class DefaultFactory implements StateProvider.Factory {

        private static DefaultFactory sInstance;

        @NonNull
        public static DefaultFactory getInstance() {
            if (sInstance == null) {
                sInstance = new DefaultFactory();
            }
            return sInstance;
        }

        @SuppressWarnings("ClassNewInstance")
        @NonNull
        @Override
        public <T extends State> T create(@NonNull Class<T> modelClass) {
            //noinspection TryWithIdenticalCatches
            try {
                return modelClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
    }
}
