package whitekr.swipeu.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import whitekr.swipeu.R
import whitekr.swipeu.utils.FirebaseAuthUtils
import whitekr.swipeu.utils.FirebaseRef

class MyPageActivity : AppCompatActivity() {

    private val TAG: String = "MyPageActivity"

    private val uid: String = FirebaseAuthUtils.getUid()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        getMyData()

    }

    private fun getMyData() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                

            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

            }
        }
        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)

    }
}