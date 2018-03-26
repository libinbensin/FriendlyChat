package com.libin.friendlychat.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.libin.friendlychat.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MessageActivity : AppCompatActivity() , MessageActivityPresenterView {

    private val RC_SIGN_IN = 123

    private var mFirebaseUser: FirebaseUser? = null

    // Choose authentication providers
    private var providers = Arrays.asList(AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build())

    override fun askUserToLogin() {
        invalidateOptionsMenu()
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .build(),
                RC_SIGN_IN)
    }

    override fun onUserSignedIn() {
        Toast.makeText(this , "You are signed in. Welcome to Friendly Chat" , Toast.LENGTH_LONG).show()
        invalidateOptionsMenu()
    }

    override fun clearMessageInputView() {
        messageInputView.setText("")
    }

    override fun showMessageSent() {
        Toast.makeText(this , "Message Sent" , Toast.LENGTH_SHORT).show()
    }

    private lateinit var mMessageActivityPresenter: MessageActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.message_fragment_container , MessageFragment.newInstance() , MessageFragment.TAG)
                    .commit()
        }

        mMessageActivityPresenter = MessageActivityPresenterImpl(this)

        mMessageActivityPresenter.onAttach()
        messageInputView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                sendButton.isEnabled = s?.isNotEmpty() ?: true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // NOP
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // NOP
            }

        })

        sendButton.setOnClickListener {
            mMessageActivityPresenter.onSendClicked(messageInputView.text.toString() , mFirebaseUser)
        }

        val auth = FirebaseAuth.getInstance()
        mFirebaseUser = auth.currentUser
        if (mFirebaseUser == null) {
            // not signed in
            askUserToLogin()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    // Successfully signed in
                    mFirebaseUser = FirebaseAuth.getInstance().currentUser!!
                    onUserSignedIn()
                }
                Activity.RESULT_CANCELED -> finish()
                else -> // Sign in failed, check response for error code
                    Toast.makeText(this , "Unable to login in" , Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(FirebaseAuth.getInstance().currentUser != null) {
            menuInflater.inflate(R.menu.sign_out, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                mFirebaseUser = null
                Toast.makeText(this , "Your signed out" , Toast.LENGTH_LONG).show()
                askUserToLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        mMessageActivityPresenter.onDestroy()
        super.onDestroy()
    }

}
