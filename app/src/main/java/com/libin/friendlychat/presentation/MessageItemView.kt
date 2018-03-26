package com.libin.friendlychat.presentation

import android.content.Context
import com.libin.friendlychat.R
import com.libin.friendlychat.common.BaseLinearLayout
import com.libin.friendlychat.common.Utilities
import com.libin.friendlychat.persistance.FriendlyMessage
import kotlinx.android.synthetic.main.view_message_item.view.*

/**
 * Created by libin on 3/24/18.
 */
class MessageItemView(context: Context?) : BaseLinearLayout(context) {

    override fun onAfterViews() {
        // NOP
    }

    override fun getLayoutId(): Int = R.layout.view_message_item

    fun bind(friendlyMessage: FriendlyMessage){
        itemText.text = friendlyMessage.text
        userTitle.text = friendlyMessage.name
        postTime.text = Utilities.formatPostTime(friendlyMessage.timeStamp)
    }

}