package whitekr.swipeu.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import whitekr.swipeu.MainActivity
import whitekr.swipeu.R
import whitekr.swipeu.utils.FirebaseAuthUtils
import whitekr.swipeu.utils.FirebaseRef

class JoinActivity : AppCompatActivity() {

    private val TAG: String = "JoinActivity"

    private lateinit var auth: FirebaseAuth

    private var name = ""
    private var gender = ""
    private var city = ""
    private var age = ""
    private var uid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = Firebase.auth

        val joinBtn: Button = findViewById(R.id.joinBtn)
        joinBtn.setOnClickListener {

            val email: TextInputEditText = findViewById(R.id.emailArea)
            val pwd: TextInputEditText = findViewById(R.id.pwdArea)

            name = findViewById<TextInputEditText>(R.id.nameArea).text.toString()
            gender = findViewById<TextInputEditText>(R.id.genderArea).text.toString()
            city = findViewById<TextInputEditText>(R.id.cityArea).text.toString()
            age = findViewById<TextInputEditText>(R.id.ageArea).text.toString()

            auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        uid = FirebaseAuthUtils.getUid()

                        val userModel = UserDataModel(
                            uid, name, gender, city, age
                        )

                        FirebaseRef.userInfoRef.child(uid).setValue(userModel)

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    }
                }
        }
    }
}