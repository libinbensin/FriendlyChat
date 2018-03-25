package com.libin.friendlychat.presentation

import com.libin.friendlychat.persistance.FriendlyMessage

/**
 * Created by libin on 3/24/18.
 */
interface MessageFragmentPresenterView {

    fun setMessages(friendlyMessages: List<FriendlyMessage>)

    fun onNewMessageReceived(friendlyMessage: FriendlyMessage)


}