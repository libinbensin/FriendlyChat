package com.libin.friendlychat.presentation

import android.net.Uri
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
    private lateinit var mMessageDatabaseReference: DatabaseReference

    private var mMessageListPresenterView = messageActivityPresenterView
    private lateinit var mFirebaseAuth: FirebaseAuth


    private var mAuthStateListenr = FirebaseAuth.AuthStateListener { var1 ->
        if (var1.currentUser != null) {
            messageActivityPresenterView.onUserSignedIn()
        }else{
            messageActivityPresenterView.onUserSignedOut()
        }
    }


    override fun onAttach() {
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mFirebaseAuth = FirebaseAuth.getInstance()
        mMessageDatabaseReference = mFirebaseDatabase.reference.child("chat").child("messages")
    }

    override fun onSendClicked(text: String, user: FirebaseUser?) {
        val friendlyMessage = FriendlyMessage(text, user?.uid, user?.displayName, "" , 0)

//        mMessageDatabaseReference.push().setValue(friendlyMessage)
        push(friendlyMessage)
        mMessageListPresenterView.clearMessageInputView()
        mMessageListPresenterView.showMessageSent()
    }


    override fun onPause() {
        FirebaseAuth.getInstance().removeAuthStateListener { mAuthStateListenr }
    }

    override fun onResume() {
        FirebaseAuth.getInstance().addAuthStateListener { mAuthStateListenr }
    }

    override fun onDestroy() {

    }

    private fun push(pojo: FriendlyMessage) {
        mMessageDatabaseReference.push()
                .apply {
                    setValue(pojo)
                    child("timeStamp").setValue(ServerValue.TIMESTAMP)
                }
    }
}