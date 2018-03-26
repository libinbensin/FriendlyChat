package com.libin.friendlychat.persistance

/**
 * Created by libin on 3/24/18.
 */
data class FriendlyMessage(val text: String?= null,
                           val userId: String?= null,
                           val name: String? = null,
                           val photoUrl : String? = null,
                           val timeStamp: Long? = null)