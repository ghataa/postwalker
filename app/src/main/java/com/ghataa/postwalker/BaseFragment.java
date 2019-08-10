package com.ghataa.postwalker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    protected abstract BasePresenter getPresenter();

    @LayoutRes
    protected abstract int getLayoutResId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().dropView();
    }
}
