package com.example.hp.motivationalquotes.controller;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;


public class AppController extends Application {

    //Log or request TAG
    public static final String TAG = AppController.class.getSimpleName();


    // A singleton instance of the application class for easy access in other places
    private static AppController mInstance;

    //Global request queue for Volley
    private RequestQueue mRequestQueue;


    //@return ApplicationController singleton instance
    public static synchronized AppController getInstance()
    {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the singleton
        mInstance = this;
    }


    //@return The Volley Request queue, the queue will be created if it is null
    public RequestQueue getRequestQueue() {


        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

//    public ImageLoader getImageLoader() {
//        getRequestQueue();
//        if (mImageLoader == null) {
//            mImageLoader = new ImageLoader(this.mRequestQueue,
//                    new LruBitmapCache());
//        }
//        return this.mImageLoader;
//    }


    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     */

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("adding request queue: %s", req.getUrl());
        getRequestQueue().add(req);
    }



    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    //Cancels all pending requests by the specified TAG, it is important
    //* to specify a TAG so that the pending/ongoing requests can be cancelled.

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
