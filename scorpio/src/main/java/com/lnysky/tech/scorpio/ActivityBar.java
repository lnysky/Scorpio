package com.lnysky.tech.scorpio;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public final class ActivityBar extends Wrapper {

    @NonNull
    private final Activity activity;

    ActivityBar(@NonNull Activity activity) {
        this.activity = activity;
    }

    @NonNull
    protected Bar getRealBar() {
        View view = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        return Utils.viewWrapper(view);
    }
}
