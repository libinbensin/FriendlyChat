package com.libin.friendlychat.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by libin on 3/24/18.
 */
abstract class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId() , container , false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onAfterViewCreated(savedInstanceState)
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun onAfterViewCreated(savedInstanceState: Bundle?)
}