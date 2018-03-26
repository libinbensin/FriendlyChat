package com.libin.friendlychat.common

import android.text.format.DateFormat

/**
 * Created by libin on 3/26/18.
 */
object Utilities {


    fun formatPostTime(postTime: Long?): String {

        return if (postTime!! < 0) {
            ""
        } else DateFormat.format("MM/dd/yy hh:mm aaa", postTime).toString()
    }
}
