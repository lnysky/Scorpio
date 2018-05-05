package com.lnysky.tech.scorpio;

import java.util.HashMap;

/**
 * Created by lny on 2018/11/28.
 */
class StateStore {

    private HashMap<String, State> mProviders = new HashMap<>();

    void put(String key, State state) {
        if (mProviders.get(key) != null) {
            throw new IllegalArgumentException("state is registered");
        }
        mProviders.put(key, state);
    }

    State get(String key) {
        return mProviders.get(key);
    }
}
