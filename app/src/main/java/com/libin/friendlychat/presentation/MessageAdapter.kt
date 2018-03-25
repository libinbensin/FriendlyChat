package com.libin.friendlychat.presentation

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.libin.friendlychat.persistance.FriendlyMessage

/**
 * Created by libin on 3/24/18.
 */
class MessageAdapter() : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private var mMessages: ArrayList<FriendlyMessage> = ArrayList()

    constructor(messages: List<FriendlyMessage>?) : this() {
        messages?.let { mMessages.addAll(it) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messageItemView = holder.itemView as MessageItemView
        messageItemView.bind(mMessages[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MessageItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return mMessages.size
    }


    class ViewHolder(messageItemView: MessageItemView) : RecyclerView.ViewHolder(messageItemView)

    fun add(friendlyMessage: FriendlyMessage) {
        mMessages.add(friendlyMessage)
        notifyDataSetChanged()
    }
}