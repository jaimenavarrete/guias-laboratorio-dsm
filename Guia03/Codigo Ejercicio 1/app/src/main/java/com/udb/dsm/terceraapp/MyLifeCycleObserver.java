package com.udb.dsm.terceraapp;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyLifeCycleObserver implements LifecycleObserver {
    private static final String TAG = "MyLifeCycleObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        Log.i(TAG,  "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        Log.i(TAG,  "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Log.i(TAG,  "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        Log.i(TAG,  "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        Log.i(TAG,  "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.i(TAG,  "onDestroy");
    }
}
