package com.lnysky.tech.scorpio;

public interface Bar {

    <T extends StateLayout.State> T get(Class<T> clazz);

}
