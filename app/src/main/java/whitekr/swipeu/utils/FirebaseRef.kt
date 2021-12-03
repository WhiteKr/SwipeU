package whitekr.swipeu.utils

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object {

        val database = Firebase.database

        val userInfoRef: DatabaseReference = database.getReference("userInfo")

    }
}