package com.libin.friendlychat.presentation

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.libin.friendlychat.persistance.FriendlyMessage

/**
 * Created by libin on 3/24/18.
 */
class MessageActivityPresenterImpl(messageActivityPresenterView: MessageActivityPresenterView): MessageActivityPresenter {

    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var mMessageDatabaseReference: DatabaseReference

    private var mMessageListPresenterView = messageActivityPresenterView

    override fun onAttach() {
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mMessageDatabaseReference = mFirebaseDatabase.reference.child("messages")
    }

    override fun onSendClicked(text: String, userName: String) {
        val friendlyMessage = FriendlyMessage(text, userName, "")
        mMessageDatabaseReference.push().setValue(friendlyMessage)
        mMessageListPresenterView.clearMessageInputView()
        mMessageListPresenterView.showMessageSent()
    }

    override fun onDestroy() {

    }

}