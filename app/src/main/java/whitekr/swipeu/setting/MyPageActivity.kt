package whitekr.swipeu.setting

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import whitekr.swipeu.R
import whitekr.swipeu.auth.UserDataModel
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

        val myImage: ImageView = findViewById(R.id.myImage)
        val myUid: TextView = findViewById(R.id.myUid)
        val myName: TextView = findViewById(R.id.myName)
        val myGender: TextView = findViewById(R.id.myGender)
        val myCity: TextView = findViewById(R.id.myCity)
        val myAge: TextView = findViewById(R.id.myAge)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val data = dataSnapshot.getValue(UserDataModel::class.java)

                val storageRef = Firebase.storage.reference.child("${data!!.uid}.png")
                storageRef.downloadUrl.addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        Glide.with(baseContext)
                            .load(task.result)
                            .into(myImage)

                    }
                }

                myUid.text = data.uid
                myName.text = data.name
                myGender.text = data.gender
                myCity.text = data.city
                myAge.text = data.age

            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

            }
        }
        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)

    }
}