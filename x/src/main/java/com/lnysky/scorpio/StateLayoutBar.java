package com.lnysky.scorpio;

import androidx.annotation.NonNull;

public final class StateLayoutBar extends Wrapper {

    @NonNull
    private final StateLayout stateLayout;

    StateLayoutBar(@NonNull StateLayout stateLayout) {
        this.stateLayout = stateLayout;
    }

    @NonNull
    protected Bar getRealBar() {
        return stateLayout;
    }
}
