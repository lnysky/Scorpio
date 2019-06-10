package com.lnysky.tech.scorpio;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Created by lny on 2018/11/28.
 */
public class Scorpio {

    public static ActivityBar with(Activity activity) {
        return new ActivityBar(activity);
    }

    public static XFragmentBar with(Fragment fragment) {
        return new XFragmentBar(fragment);
    }

    public static FragmentBar with(android.app.Fragment fragment) {
        return new FragmentBar(fragment);
    }

    public static StateLayoutBar with(@NonNull StateLayout stateLayout) {
        return new StateLayoutBar(stateLayout);
    }

    private static StateLayout with(@NonNull View view) {
        if (view instanceof StateLayout) {
            return (StateLayout) view;
        }

        StateLayout layout = new StateLayout(view.getContext());

        ViewParent viewParent = view.getParent();
        if (viewParent instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) viewParent;
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            int index = parent.indexOfChild(view);
            parent.removeView(view);

            parent.addView(layout, index, lp);
        }

        layout.setContentView(view);
        return layout;
    }

    public static class ActivityBar extends AbsBar {

        private Activity activity;

        ActivityBar(Activity activity) {
            this.activity = activity;
        }

        protected Bar with() {
            View view = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            return Scorpio.with(view);
        }
    }

    public static class XFragmentBar extends AbsBar {

        private Fragment fragment;

        XFragmentBar(Fragment fragment) {
            this.fragment = fragment;
        }

        public View wrapper(View view) {
            return Scorpio.with(view);
        }

        protected Bar with() {
            View view = fragment.getView();
            if (view == null) {
                throw new NullPointerException();
            }

            if (view instanceof StateLayout) {
                return Scorpio.with(view);
            } else {
                throw new IllegalStateException("set root view to be StateLayout " +
                        "or wrapper the root view in onCreateView and return the wrapper view");
            }
        }
    }

    public static class FragmentBar extends AbsBar {

        private android.app.Fragment fragment;

        FragmentBar(android.app.Fragment fragment) {
            this.fragment = fragment;
        }

        public View wrapper(View view) {
            return Scorpio.with(view);
        }

        protected Bar with() {
            View view = fragment.getView();
            if (view == null) {
                throw new NullPointerException();
            }

            if (view instanceof StateLayout) {
                return Scorpio.with(view);
            } else {
                throw new IllegalStateException("set root view to be StateLayout " +
                        "or wrapper the root view in onCreateView and return the wrapper view");
            }
        }
    }

    public static class StateLayoutBar extends AbsBar {

        private StateLayout stateLayout;

        StateLayoutBar(@NonNull StateLayout stateLayout) {
            this.stateLayout = stateLayout;
        }

        protected Bar with() {
            return stateLayout;
        }
    }

}
