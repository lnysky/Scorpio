package com.lnysky.tech.scorpio;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by lny on 2018/11/28.
 */
final class StateProvider {

    private final StateStore stateStore;

    StateProvider() {
        this.stateStore = new StateStore();
    }

    <T extends State> T get(Class<T> clazz, @NonNull Factory factory) {
        String canonicalName = clazz.getCanonicalName();
        State state = stateStore.get(canonicalName);
        if (clazz.isInstance(state)) {
            //noinspection unchecked
            return (T) state;
        }

        state = factory.create(clazz);
        stateStore.put(canonicalName, state);

        //noinspection unchecked
        return (T) state;
    }

    interface Factory {
        <T extends State> T create(Class<T> clazz);
    }

    static class DefaultFactory implements Factory {

        StateSwitcher switcher;

        DefaultFactory(StateSwitcher switcher) {
            this.switcher = switcher;
        }

        @SuppressWarnings("ClassNewInstance")
        @NonNull
        @Override
        public <T extends State> T create(@NonNull Class<T> modelClass) {
            //noinspection TryWithIdenticalCatches
            try {
                return modelClass.getConstructor(StateSwitcher.class).newInstance(switcher);
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
    }

}
