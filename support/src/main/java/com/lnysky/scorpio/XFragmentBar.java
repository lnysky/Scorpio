package com.lnysky.scorpio;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;


public final class XFragmentBar extends Wrapper {

    @NonNull
    private final Fragment fragment;

    XFragmentBar(@NonNull Fragment fragment) {
        this.fragment = fragment;
    }

    public StateLayout wrapper(View view) {
        return Utils.viewWrapper(view);
    }

    @NonNull
    protected Bar getRealBar() {
        View view = fragment.getView();
        if (view == null) {
            throw new NullPointerException();
        }

        if (view instanceof Bar) {
            return (Bar) view;
        }

        throw new IllegalStateException("set root view to be StateLayout " +
                "or wrapper the root view in onCreateView and return the wrapper view");
    }
}
