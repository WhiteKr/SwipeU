package whitekr.swipeu.auth

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import whitekr.swipeu.R

class JoinActivity : AppCompatActivity() {

    private val TAG: String = "JoinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val joinBtn: Button = findViewById(R.id.joinBtn)
        joinBtn.setOnClickListener {

            val email: TextInputEditText = findViewById(R.id.emailArea)
            val pwd: TextInputEditText = findViewById(R.id.pwdArea)

            Log.d(TAG, email.text.toString())
            Log.d(TAG, pwd.text.toString())

        }
    }
}