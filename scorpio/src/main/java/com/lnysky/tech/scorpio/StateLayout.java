package com.lnysky.tech.scorpio;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lny on 2018/11/28.
 */
@SuppressLint("ViewConstructor")
final class StateLayout extends FrameLayout implements Bar, StateSwitcher {

    private final LayoutInflater inflater;
    private final StateProvider stateProvider;
    private final StateProvider.DefaultFactory defaultFactory;
    private Map<State, StateViewHolder> holderMap = new HashMap<>();

    private State currentState;

    public StateLayout(View view) {
        super(view.getContext());
        inflater = LayoutInflater.from(view.getContext());
        stateProvider = new StateProvider();
        defaultFactory = new StateProvider.DefaultFactory();
        init(view);
    }

    private void init(View view) {
        switchState(get(Content.class, new ContentStateFactory(view)));
    }

    @Override
    public void switchState(State state) {
        show(state, getStateViewHolder(state));
    }

    public void show(State state, StateViewHolder viewHolder) {
        if (!state.equals(currentState)) {
            if (currentState != null) {
                //noinspection unchecked
                currentState.onSwitchState(getStateViewHolder(currentState), false);
            }

            //noinspection unchecked
            state.onSwitchState(viewHolder, true);
            currentState = state;
        }
    }

    @NonNull
    private StateViewHolder getStateViewHolder(State state) {
        StateViewHolder viewHolder = holderMap.get(state);
        if (viewHolder != null) {
            return viewHolder;
        }
        viewHolder = state.onCreateStateViewHolder(inflater, this);
        View view = viewHolder.getView();
        if (view == null) {
            throw new NullPointerException("view must be non-null");
        }
        ViewParent parent = view.getParent();
        if (parent == null) {
            addView(view);
        } else {
            throw new IllegalStateException("view's parent must be null");
        }
        holderMap.put(state, viewHolder);
        return viewHolder;
    }

    @Override
    public <T extends State> T get(Class<T> clazz) {
        return get(clazz, defaultFactory);
    }

    public <T extends State> T get(Class<T> clazz, @NonNull StateProvider.Factory factory) {
        T state = stateProvider.get(clazz, factory);
        state.setSwitcher(this);
        return state;
    }

    static class ContentStateFactory extends StateProvider.DefaultFactory {

        private View view;

        ContentStateFactory(View view) {
            this.view = view;
        }

        @SuppressWarnings("ClassNewInstance")
        @NonNull
        @Override
        public <T extends State> T create(@NonNull Class<T> modelClass) {
            //noinspection TryWithIdenticalCatches
            try {
                return modelClass.getConstructor(View.class).newInstance(view);
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
