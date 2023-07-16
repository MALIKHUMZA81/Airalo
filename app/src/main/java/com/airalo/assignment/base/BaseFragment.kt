package com.airalo.assignment.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.kaopiz.kprogresshud.KProgressHUD
import com.airalo.assignment.core.utils.DialogUtils
import com.airalo.assignment.ui.activity.MainActivity

/**
 * The BaseFragment.kt
 */
abstract class BaseFragment : Fragment {

    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected lateinit var progressDialog: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = DialogUtils.showProgressDialog(getRootActivity(), cancelable = false)
    }

    protected fun getRootActivity(): MainActivity = activity as MainActivity
}
