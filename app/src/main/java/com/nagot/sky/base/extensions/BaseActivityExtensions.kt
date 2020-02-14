package com.nagot.sky.base.extensions

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nagot.sky.base.BaseActivity
import kotlin.math.max

fun BaseActivity.toast(message: String) {

    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun BaseActivity.checkIsMaterialVersion() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun BaseActivity.circularRevealedAtCenter(view: View) {
    val cx = (view.left + view.right) / 2
    val cy = (view.top + view.bottom) / 2
    val finalRadius = max(view.width, view.height)

    if (checkIsMaterialVersion() && view.isAttachedToWindow) {
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
        view.visible()
        view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black))
        anim.duration = 550
        anim.start()
    }
}

fun BaseActivity.requestGlideListener(view: View): RequestListener<Drawable> {
    return object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            circularRevealedAtCenter(view)
            return false
        }
    }
}