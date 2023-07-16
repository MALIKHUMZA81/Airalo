package com.airalo.assignment.ui.activity

import android.os.Bundle
import com.airalo.assignment.R
import com.airalo.assignment.base.BaseActivity
import com.airalo.assignment.core.extensions.replaceFragmentSafely
import com.airalo.assignment.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity.kt, Main activity class, launcher activity
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentSafely(HomeFragment())
    }
}
