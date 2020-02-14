package com.nagot.sky.ui.activities

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.nagot.sky.R
import com.nagot.sky.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun layoutRes(): Int {

        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavController()
    }

    private fun setupNavController() {
        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = findNavController(R.id.nav_host_fragment)

        mAppBarConfiguration =
            AppBarConfiguration.Builder(R.id.movies_fragment_dest)
                .build()

        setupActionBarWithNavController(navController, mAppBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(mAppBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
