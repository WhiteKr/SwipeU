package whitekr.swipeu.utils

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthUtils {

    companion object {

        private val auth: FirebaseAuth = FirebaseAuth.getInstance()

        fun getUid(): String = auth.currentUser?.uid.toString()

    }

}