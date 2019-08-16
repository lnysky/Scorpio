package com.lnysky.tech.scorpio;

import androidx.annotation.NonNull;

public abstract class Wrapper implements Bar {

    public Content content() {
        return get(Content.class);
    }

    public Loading loading() {
        return get(Loading.class);
    }

    public Empty empty() {
        return get(Empty.class);
    }

    public Error error() {
        return get(Error.class);
    }

    @Override
    public <T extends State> T get(Class<T> clazz) {
        return getRealBar().get(clazz);
    }

    @NonNull
    protected abstract Bar getRealBar();

}
