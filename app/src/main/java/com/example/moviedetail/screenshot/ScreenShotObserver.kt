package com.example.moviedetail.screenshot

import android.content.ContentResolver
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log

class ScreenShotObserver(
    handler: Handler?,
    private val mContentResolver: ContentResolver,
    private val mListener: ShotWatch.Listener
) : ContentObserver(handler) {
    private val PROJECTION = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATA
    )
    private val MEDIA_EXTERNAL_URI_STRING = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()
    private val FILE_NAME_PREFIX = "screenshot"
    private val PATH_SCREENSHOT = "screenshots/"
    override fun deliverSelfNotifications(): Boolean {
        return super.deliverSelfNotifications()
    }

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        Log.e("ScreenShotObserver","OnChange 0 Called...")
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        Log.e("ScreenShotObserver","OnChange Called...")
        if (isSingleImageFile(uri)) {
            handleItem(uri)
        }
    }

    private fun isSingleImageFile(uri: Uri?): Boolean {
        return uri.toString().matches("$MEDIA_EXTERNAL_URI_STRING/[0-9]+".toRegex())
    }

    private fun handleItem(uri: Uri?) {
        var cursor: Cursor? = null
        try {
            cursor = mContentResolver.query(uri!!, PROJECTION, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                val screenshotData = generateScreenshotDataFromCursor(cursor)
                if (screenshotData != null) {
                    Handler(Looper.getMainLooper()).post {
                        mListener.onScreenShotTaken(
                            screenshotData
                        )
                    }
                }
            }
        } finally {
            cursor?.close()
        }
    }

    private fun generateScreenshotDataFromCursor(cursor: Cursor): ScreenshotData? {
        val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID))
        val fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
        val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        return if (isPathScreenshot(path) && isFileScreenshot(fileName)) {
            ScreenshotData(id, fileName, path)
        } else {
            null
        }
    }

    private fun isFileScreenshot(fileName: String): Boolean {
        return fileName.toLowerCase().startsWith(FILE_NAME_PREFIX)
    }

    private fun isPathScreenshot(path: String): Boolean {
        return path.toLowerCase().contains(PATH_SCREENSHOT)
    }
}