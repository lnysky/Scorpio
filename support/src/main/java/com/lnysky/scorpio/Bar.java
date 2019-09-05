package com.lnysky.scorpio;

public interface Bar {

    <T extends StateLayout.State> T get(Class<T> clazz);

}
