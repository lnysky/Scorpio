package com.lnysky.tech.scorpio;

import java.util.HashMap;

/**
 * Created by lny on 2018/11/28.
 */
class ProviderStore {

    private HashMap<String, Provider> mProviders = new HashMap<>();

    void put(String key, Provider provider) {
        if (mProviders.get(key) != null) {
            throw new IllegalArgumentException("provider is registered");
        }
        mProviders.put(key, provider);
    }

    Provider get(String key) {
        return mProviders.get(key);
    }
}
