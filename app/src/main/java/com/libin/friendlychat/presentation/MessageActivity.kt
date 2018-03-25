package com.libin.friendlychat.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.libin.friendlychat.R
import kotlinx.android.synthetic.main.activity_main.*

class MessageActivity : AppCompatActivity() , MessageActivityPresenterView {

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
            mMessageActivityPresenter.onSendClicked(messageInputView.text.toString() , "Anonymous")
        }
    }

    override fun onDestroy() {
        mMessageActivityPresenter.onDestroy()
        super.onDestroy()
    }

}
