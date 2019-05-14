package com.lnysky.tech.scorpio;

import java.util.HashMap;

/**
 * Created by lny on 2018/11/28.
 */
class StateStore {

    private HashMap<String, State> store = new HashMap<>();

    void put(String key, State state) {
        if (store.get(key) != null) {
            throw new IllegalArgumentException("state is registered");
        }
        store.put(key, state);
    }

    State get(String key) {
        return store.get(key);
    }
}
