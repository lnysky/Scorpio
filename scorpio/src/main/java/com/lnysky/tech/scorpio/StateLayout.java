package com.lnysky.tech.scorpio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        return get(clazz, State.DefaultFactory.getInstance());
    }

    <T extends State> T get(Class<T> clazz, @NonNull StateProvider.Factory factory) {
        if (!isSetContentView) {
            throw new IllegalStateException("please set the content view");
        }
        T state = stateProvider.get(clazz, factory);
        state.switcher = this;
        return state;
    }

    public static class ViewHolder {

        @NonNull
        public final View stateView;

        public ViewHolder(@NonNull View stateView) {
            this.stateView = stateView;
        }

    }
}
