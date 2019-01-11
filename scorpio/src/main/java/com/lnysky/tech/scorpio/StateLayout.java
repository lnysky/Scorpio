package com.lnysky.tech.scorpio;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * Created by lny on 2018/11/28.
 */
@SuppressLint("ViewConstructor")
final class StateLayout extends FrameLayout implements Bar {

    private final LayoutInflater inflater;
    private final Factory factory;
    private final ProviderStore providerStore;

    private Holder currentHolder;

    public StateLayout(View view) {
        super(view.getContext());
        inflater = LayoutInflater.from(view.getContext());
        factory = new NewInstanceFactory();
        providerStore = new ProviderStore();
        initContentView(view);
    }

    private void initContentView(View view) {
        Content provider = createProvider(Content.class);
        provider.setView(view);
        show(bindHolder(provider));
    }

    private <T extends Provider> T createProvider(Class<T> clazz) {
        String canonicalName = clazz.getCanonicalName();
        Provider provider = providerStore.get(canonicalName);
        if (provider == null) {
            provider = factory.create(clazz);
            providerStore.put(canonicalName, provider);
        }
        //noinspection unchecked
        return (T) provider;
    }

    private void show(Holder holder) {
        if (!holder.equals(currentHolder)) {
            if (currentHolder != null) {
                currentHolder.onDisplay(false);
            }
            holder.onDisplay(true);
            currentHolder = holder;
        }
    }

    @NonNull
    private Holder bindHolder(Provider provider) {
        Holder holder = provider.getHolder();
        if (holder != null) {
            return holder;
        }
        holder = new Holder(this, provider);
        View view = holder.makeView(inflater, this);
        if (view == null) {
            throw new NullPointerException("view must be non-null");
        }
        ViewParent parent = view.getParent();
        if (parent == null) {
            addView(view);
        } else {
            throw new IllegalStateException("view's parent must be null");
        }
        return holder;
    }

    @Override
    public Content content() {
        return get(Content.class);
    }

    @Override
    public Loading loading() {
        return get(Loading.class);
    }

    @Override
    public Empty empty() {
        return get(Empty.class);
    }

    @Override
    public Error error() {
        return get(Error.class);
    }

    @Override
    public <T extends Provider> T get(Class<T> clazz) {
        T provider = createProvider(clazz);
        bindHolder(provider);
        return provider;
    }

    interface Factory {
        <T extends Provider> T create(Class<T> clazz);
    }

    static class NewInstanceFactory implements Factory {

        @SuppressWarnings("ClassNewInstance")
        @NonNull
        @Override
        public <T extends Provider> T create(@NonNull Class<T> modelClass) {
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

    static class Holder {

        private final StateLayout layout;
        private final Provider provider;
        private View view;

        Holder(StateLayout layout, Provider provider) {
            this.layout = layout;
            this.provider = provider;
            this.provider.setHolder(this);
        }

        View getView() {
            return view;
        }

        View makeView(LayoutInflater inflater, ViewGroup parent) {
            if (view != null) return view;
            view = provider.onCreateView(inflater, parent);
            return view;
        }

        void show() {
            layout.show(this);
        }

        void onDisplay(boolean display) {
            if (view != null) {
                view.setVisibility(display ? VISIBLE : GONE);
            }
            provider.onDisplay(display);
        }
    }

}
