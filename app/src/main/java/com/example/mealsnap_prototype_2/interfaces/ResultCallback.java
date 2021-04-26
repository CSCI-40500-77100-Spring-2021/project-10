package com.example.mealsnap_prototype_2.interfaces;

public interface ResultCallback<T, E extends Exception> {
    void onSuccess(T result);

    void onError(E error);
}
