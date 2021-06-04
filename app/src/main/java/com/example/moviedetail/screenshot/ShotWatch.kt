package com.example.moviedetail.screenshot

import android.content.ContentResolver
import android.database.ContentObserver
import android.os.Handler
import android.os.HandlerThread
import android.provider.MediaStore

class ShotWatch(contentResolver: ContentResolver, listener: Listener) {
    private val mHandlerThread: HandlerThread = HandlerThread("ShotWatch")
    private val mHandler: Handler
    private val mContentResolver: ContentResolver
    private val mContentObserver: ContentObserver
    private val mListener: Listener
    fun register() {
        mContentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            mContentObserver
        )
    }

    fun unregister() {
        mContentResolver.unregisterContentObserver(mContentObserver)
    }

    interface Listener {
        fun onScreenShotTaken(screenshotData: ScreenshotData?)
    }

    init {
        mHandlerThread.start()
        mHandler = Handler(mHandlerThread.looper)
        mContentResolver = contentResolver
        mContentObserver = ScreenShotObserver(mHandler, contentResolver, listener)
        mListener = listener
    }
}