package com.libin.friendlychat.presentation

/**
 * Created by libin on 3/24/18.
 */
interface MessageActivityPresenterView {

    fun clearMessageInputView()

    fun showMessageSent()

    fun onUserSignedOut()

    fun onUserSignedIn()
}