package com.lnysky.scorpio;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.lang.reflect.InvocationTargetException;

/**
 * Created by lny on 2018/11/28.
 */
public final class Content extends StateLayout.State<StateLayout.ViewHolder> {

    private View view;

    public Content(View view) {
        this.view = view;
    }

    @NonNull
    @Override
    public StateLayout.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new StateLayout.ViewHolder(view);
    }

    static class Factory implements StateLayout.Factory {

        private View view;

        Factory(View view) {
            this.view = view;
        }

        @SuppressWarnings("ClassNewInstance")
        @NonNull
        @Override
        public <T extends StateLayout.State> T create(@NonNull Class<T> modelClass) {
            //noinspection TryWithIdenticalCatches
            try {
                return modelClass.getConstructor(View.class).newInstance(view);
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
    }
}
