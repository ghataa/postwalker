package com.ghataa.postwalker;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();
}
