package whitekr.swipeu.setting

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import whitekr.swipeu.R
import whitekr.swipeu.auth.IntroActivity

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val myPageBtn: Button = findViewById(R.id.myPageBtn)
        myPageBtn.setOnClickListener {

            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)

        }

        val logoutBtn: Button = findViewById(R.id.logoutBtn)
        logoutBtn.setOnClickListener {

            val auth = Firebase.auth
            auth.signOut()

            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)

        }
    }
}