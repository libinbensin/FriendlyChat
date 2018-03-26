package com.libin.friendlychat.presentation

import com.google.firebase.database.*
import com.libin.friendlychat.persistance.FriendlyMessage

/**
 * Created by libin on 3/24/18.
 */
class MessageFragmentPresenterImpl(mMessageFragmentPresenterView: MessageFragmentPresenterView): MessageFragmentPresenter{

    private var mChildEventListener: ChildEventListener = object: ChildEventListener{
        override fun onCancelled(dataSnapShot: DatabaseError?) {
            // NOP
        }

        override fun onChildMoved(dataSnapShot: DataSnapshot?, p1: String?) {
            // NOP
        }

        override fun onChildChanged(dataSnapShot: DataSnapshot?, p1: String?) {
            // NOP
        }

        override fun onChildAdded(dataSnapShot: DataSnapshot?, p1: String?) {
            val friendlyMessage = dataSnapShot!!.getValue(FriendlyMessage::class.java)
            mMessageFragmentPresenterView.onNewMessageReceived(friendlyMessage!!)
        }

        override fun onChildRemoved(dataSnapShot: DataSnapshot?) {
            // NOP
        }
    }

    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var mMessageDatabaseReference: DatabaseReference

    override fun onAttach() {
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mMessageDatabaseReference = mFirebaseDatabase.reference.child("chat").child("messages")
        mMessageDatabaseReference.addChildEventListener(mChildEventListener)

    }

    override fun onDestroy() {
        mMessageDatabaseReference.removeEventListener(mChildEventListener)
    }

}