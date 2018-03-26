package com.libin.friendlychat.presentation

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.libin.friendlychat.R
import com.libin.friendlychat.common.BaseFragment
import com.libin.friendlychat.persistance.FriendlyMessage
import kotlinx.android.synthetic.main.fragment_message.*

/**
 * Created by libin on 3/24/18.
 */
class MessageFragment: BaseFragment() , MessageFragmentPresenterView{
    override fun setMessages(friendlyMessages: List<FriendlyMessage>) {

    }

    override fun onNewMessageReceived(friendlyMessage: FriendlyMessage) {
        mMessageAdapter.add(friendlyMessage)
        messageListView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    private lateinit var mMessageAdapter: MessageAdapter
    private lateinit var mMessageFragmentPresenter: MessageFragmentPresenter

    override fun getLayoutId(): Int = R.layout.fragment_message

    override fun onAfterViewCreated(savedInstanceState: Bundle?) {
        mMessageFragmentPresenter = MessageFragmentPresenterImpl(this)
        mMessageAdapter = MessageAdapter(null)
        messageListView.adapter= mMessageAdapter
        messageListView.layoutManager = LinearLayoutManager(this.context)
        messageListView.addItemDecoration(DividerItemDecoration(context , DividerItemDecoration.VERTICAL))
        mMessageFragmentPresenter.onAttach()
    }

    override fun onDetach() {
        mMessageFragmentPresenter.onDestroy()
        super.onDetach()
    }

    companion object {
        fun newInstance() : MessageFragment = MessageFragment()
        const val TAG = "FriendlyMessage"
    }


}