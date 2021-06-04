package com.example.moviedetail.view

import android.Manifest
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedetail.R
import com.example.moviedetail.screenshot.ScreenshotData
import com.example.moviedetail.screenshot.ShotWatch
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {
    var shotWatch: ShotWatch? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        requestPermission()
       /* val listener = object : ShotWatch.Listener {
            override fun onScreenShotTaken(screenshotData: ScreenshotData?) {
                imageView.setImageURI(Uri.parse(screenshotData?.path))
            }

        }
        shotWatch = ShotWatch(contentResolver, listener)*/
    }

    private fun requestPermission() {
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(
                object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        val listener = object : ShotWatch.Listener {
                            override fun onScreenShotTaken(screenshotData: ScreenshotData?) {
                                imageView.setImageURI(Uri.parse(screenshotData?.path))
                            }

                        }
                        shotWatch = ShotWatch(contentResolver, listener)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        // check for permanent denial of permission
                        if (response?.isPermanentlyDenied!!) {

                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) {
                        token?.continuePermissionRequest()
                    }

                }).check()
    }

    override fun onResume() {
        super.onResume()
        shotWatch?.register()
    }

    override fun onPause() {
        super.onPause()
        shotWatch?.unregister()
    }
}