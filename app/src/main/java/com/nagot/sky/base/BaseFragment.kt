package com.nagot.sky.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment() {

    private lateinit var activity: BaseActivity

    @LayoutRes
    protected abstract fun layoutRes(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(layoutRes(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity) {
            activity = context
        }
    }

    fun getBaseActivity() = activity
}