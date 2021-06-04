package com.example.moviedetail.view.facebook

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedetail.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog
import kotlinx.android.synthetic.main.activity_facebook.*


class FacebookActivity : AppCompatActivity() {
    private var callbackManager: CallbackManager? = null
    private val TAG = "FacebookActivity"

    private val accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(
            oldAccessToken: AccessToken?,
            currentAccessToken: AccessToken?
        ) {
            if (currentAccessToken == null) {
                LoginManager.getInstance().logOut()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        accessTokenTracker.stopTracking()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)
        callbackManager = CallbackManager.Factory.create()
        login_button.setPermissions(mutableListOf("user_gender", "user_friends"))
        image_view.setImageResource(R.drawable.image)
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.e(TAG, "onSuccess Called")
            }

            override fun onCancel() {
                Log.e(TAG, "onCancel Called")
            }

            override fun onError(error: FacebookException?) {
                Log.e(TAG, "onError Called")
            }
        })
        shareButton.setOnClickListener {
            val drawable = image_view.drawable as BitmapDrawable
            val bitmap = drawable.bitmap

            val sharePhoto = SharePhoto.Builder().setBitmap(bitmap).build()
            val sharePhotoContent = SharePhotoContent.Builder().addPhoto(sharePhoto).build()
            val shareDialog = ShareDialog(this);
            if (ShareDialog.canShow(SharePhotoContent::class.java)) {
                shareDialog.show(sharePhotoContent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}