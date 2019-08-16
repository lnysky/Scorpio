package com.lnysky.tech.scorpio;

import android.view.View;

import androidx.annotation.NonNull;

public final class FragmentBar extends Wrapper {

    @NonNull
    private final android.app.Fragment fragment;

    FragmentBar(@NonNull android.app.Fragment fragment) {
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
