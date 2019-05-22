package com.lnysky.tech.scorpio;

public abstract class AbsBar implements Bar {

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
        return with().get(clazz);
    }

    protected abstract Bar with();

}
