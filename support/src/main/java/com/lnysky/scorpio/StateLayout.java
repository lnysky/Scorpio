package com.lnysky.scorpio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;


import java.util.HashMap;

/**
 * Created by lny on 2018/11/28.
 */
final public class StateLayout extends FrameLayout implements Bar, StateSwitcher {

    private LayoutInflater inflater;
    private StateProvider stateProvider;
    private State currentState;
    private boolean isSetContentView = false;

    public StateLayout(Context context) {
        this(context, null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflater = LayoutInflater.from(context);
        stateProvider = new StateProvider();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("only one child");
        }

        if (isSetContentView) {
            return;
        }

        if (getChildCount() == 0) {
            return;
        }

        View view = getChildAt(0);
        setContentView(view);
    }

    public void setContentView(View view) {
        if (isSetContentView) {
            throw new IllegalStateException("the content view is set");
        }
        isSetContentView = true;
        get(Content.class, new Content.Factory(view)).show();
    }

    @Override
    public void switchState(State state) {
        if (!state.equals(currentState)) {
            if (currentState != null) {
                checkState(currentState).display(false);
            }

            checkState(state).display(true);
            currentState = state;
        }
    }

    private State checkState(State state) {
        ViewHolder holder = state.getViewHolder();
        if (holder != null) return state;

        holder = state.createViewHolder(inflater, this);
        ViewParent parent = holder.stateView.getParent();
        if (parent == this) {
            return state;
        }

        if (parent == null) {
            addView(holder.stateView);
        } else {
            throw new IllegalStateException("view's parent must be null");
        }
        return state;
    }

    @Override
    public <T extends State> T get(Class<T> clazz) {
        return get(clazz, DefaultFactory.getInstance());
    }

    <T extends State> T get(Class<T> clazz, @NonNull Factory factory) {
        if (!isSetContentView) {
            throw new IllegalStateException("please set the content view");
        }
        T state = stateProvider.get(clazz, factory);
        state.switcher = this;
        return state;
    }

    interface Factory {
        <T extends State> T create(Class<T> clazz);
    }

    static class DefaultFactory implements Factory {

        private static DefaultFactory sInstance;

        private DefaultFactory() {
            //no instance
        }

        @NonNull
        static DefaultFactory getInstance() {
            if (sInstance == null) {
                sInstance = new DefaultFactory();
            }
            return sInstance;
        }

        @SuppressWarnings("ClassNewInstance")
        @NonNull
        @Override
        public <T extends State> T create(@NonNull Class<T> modelClass) {
            //noinspection TryWithIdenticalCatches
            try {
                return modelClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
    }

    static class StateStore {

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

    static final class StateProvider {

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
    }

    public static class ViewHolder {

        @NonNull
        public final View stateView;

        public ViewHolder(@NonNull View stateView) {
            this.stateView = stateView;
        }
    }

    public abstract static class State<VH extends ViewHolder> {

        StateSwitcher switcher;
        private VH viewHolder;
        private boolean showing;

        VH createViewHolder(LayoutInflater inflater, ViewGroup parent) {
            if (viewHolder != null) {
                throw new IllegalStateException();
            }
            viewHolder = onCreateViewHolder(inflater, parent);
            return viewHolder;
        }

        VH getViewHolder() {
            return viewHolder;
        }

        public boolean isShowing() {
            return showing;
        }

        public final void show() {
            if (switcher == null) throw new NullPointerException();
            switcher.switchState(this);
        }

        void display(boolean showing) {
            this.showing = showing;
            onSwitchState(viewHolder, showing);
        }

        protected void onSwitchState(VH holder, boolean showing) {
            holder.stateView.setVisibility(showing ? VISIBLE : GONE);
        }

        @NonNull
        protected abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);
    }
}
