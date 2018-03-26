package com.libin.friendlychat.common

/**
 * Created by libin on 3/24/18.
 */
interface BasePresenter{
    fun onAttach()

    fun onPause()

    fun onResume()

    fun onDestroy()
}