package com.lnysky.tech.scorpio;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;

/**
 * Created by lny on 2018/11/28.
 */
public class Scorpio {

    public static Bar with(Activity activity) {
        View view = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        return with(view, false);
    }

    public static Bar with(Fragment fragment) {
        View view = fragment.getView();
        return with(view, true);
    }

    public static Bar with(android.app.Fragment fragment) {
        View view = fragment.getView();
        return with(view, true);
    }

    public static Bar with(View view) {
        return with(view, true);
    }

    private static Bar with(View view, boolean isParent) {
        if (view == null) {
            throw new NullPointerException("view must be not null");
        }

        ViewParent viewParent = view.getParent();
        if (viewParent instanceof RelativeLayout) {
            throw new IllegalStateException("the parent of view is RelativeLayout, not support");
        }

        if (isParent) {
            if (viewParent instanceof Bar) {
                return (Bar) viewParent;
            }
        } else {
            if (view instanceof Bar) {
                return (Bar) view;
            }
        }

        if (viewParent instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) viewParent;
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            int index = parent.indexOfChild(view);
            parent.removeView(view);

            StateLayout layout = new StateLayout(view);
            parent.addView(layout, index, lp);
            return layout;
        } else {
            throw new IllegalStateException("view must have a non-null ViewGroup viewParent");
        }
    }

    public static Content content(Activity activity) {
        return with(activity).get(Content.class);
    }

    public static Content content(Fragment fragment) {
        return with(fragment).get(Content.class);
    }

    public static Content content(android.app.Fragment fragment) {
        return with(fragment).get(Content.class);
    }

    public static Content content(View view) {
        return with(view).get(Content.class);
    }

    public static Loading loading(Activity activity) {
        return with(activity).get(Loading.class);
    }

    public static Loading loading(Fragment fragment) {
        return with(fragment).get(Loading.class);
    }

    public static Loading loading(android.app.Fragment fragment) {
        return with(fragment).get(Loading.class);
    }

    public static Loading loading(View view) {
        return with(view).get(Loading.class);
    }

    public static Empty empty(Activity activity) {
        return with(activity).get(Empty.class);
    }

    public static Empty empty(Fragment fragment) {
        return with(fragment).get(Empty.class);
    }

    public static Empty empty(android.app.Fragment fragment) {
        return with(fragment).get(Empty.class);
    }

    public static Empty empty(View view) {
        return with(view).get(Empty.class);
    }

    public static Error error(Activity activity) {
        return with(activity).get(Error.class);
    }

    public static Error error(Fragment fragment) {
        return with(fragment).get(Error.class);
    }

    public static Error error(android.app.Fragment fragment) {
        return with(fragment).get(Error.class);
    }

    public static Error error(View view) {
        return with(view).get(Error.class);
    }

}
