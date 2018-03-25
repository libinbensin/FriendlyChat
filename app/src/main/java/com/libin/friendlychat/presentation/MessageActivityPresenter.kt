package com.libin.friendlychat.presentation

import com.libin.friendlychat.common.BasePresenter

/**
 * Created by libin on 3/24/18.
 */
interface MessageActivityPresenter : BasePresenter{
    fun onSendClicked(text: String, userName: String)
}