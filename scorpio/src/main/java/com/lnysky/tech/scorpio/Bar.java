package com.lnysky.tech.scorpio;

public interface Bar {

    <T extends State> T get(Class<T> clazz);

}
