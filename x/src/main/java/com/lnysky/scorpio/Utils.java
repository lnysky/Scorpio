package com.lnysky.scorpio;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;

class Utils {

    static StateLayout viewWrapper(@NonNull View view) {
        if (view instanceof StateLayout) {
            return (StateLayout) view;
        }

        StateLayout layout = new StateLayout(view.getContext());

        ViewParent viewParent = view.getParent();
        if (viewParent instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) viewParent;
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            int index = parent.indexOfChild(view);
            parent.removeView(view);

            parent.addView(layout, index, lp);
        }

        layout.setContentView(view);
        return layout;
    }
}
