package com.libin.friendlychat.common

import android.content.Context
import android.view.View
import android.widget.LinearLayout

/**
 * Created by libin on 3/24/18.
 */
abstract class BaseLinearLayout(context: Context?) : LinearLayout(context) {

    private var bAlreadyInflated: Boolean = false

    init {
        onFinishInflate()
    }
    override fun onFinishInflate() {
        if (!bAlreadyInflated) {
            bAlreadyInflated = true
            View.inflate(context, getLayoutId(), this)
            onAfterViews()
        }
        super.onFinishInflate()
    }

    protected abstract fun onAfterViews()
    protected abstract fun getLayoutId(): Int
}