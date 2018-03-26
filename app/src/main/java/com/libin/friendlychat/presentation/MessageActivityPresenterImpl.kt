package com.libin.friendlychat.presentation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.libin.friendlychat.persistance.FriendlyMessage

/**
 * Created by libin on 3/24/18.
 */
class MessageActivityPresenterImpl(messageActivityPresenterView: MessageActivityPresenterView): MessageActivityPresenter {

    private lateinit var mFirebaseDatabase: FirebaseDatabase

    private var mMessageDatabaseReference: DatabaseReference? = null

    private var mMessageListPresenterView = messageActivityPresenterView

    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onAttach() {
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mFirebaseAuth = FirebaseAuth.getInstance()
        mMessageDatabaseReference = mFirebaseDatabase.reference.child("chat").child("messages")
    }

    override fun onSendClicked(text: String, user: FirebaseUser?) {
        val friendlyMessage = FriendlyMessage(text, user?.uid, user?.displayName, "" , 0)

        // push the message
        mMessageDatabaseReference!!.push()
                .apply {
                    setValue(friendlyMessage)
                    child("timeStamp").setValue(ServerValue.TIMESTAMP)
                }

        mMessageListPresenterView.clearMessageInputView()
        mMessageListPresenterView.showMessageSent()
    }

    override fun onDestroy() {
        mMessageDatabaseReference = null
    }
}