package com.lnysky.tech.scorpio;

public interface Bar {

    Loading loading();

    Content content();

    Empty empty();

    Error error();

    <T extends Provider> T get(Class<T> clazz);
}
