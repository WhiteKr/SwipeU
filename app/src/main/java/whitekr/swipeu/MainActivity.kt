package whitekr.swipeu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import whitekr.swipeu.auth.UserDataModel
import whitekr.swipeu.setting.SettingActivity
import whitekr.swipeu.slider.CardStackAdapter
import whitekr.swipeu.utils.FirebaseRef

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    lateinit var cardStackAdapter: CardStackAdapter
    lateinit var manager: CardStackLayoutManager

    val userDataList: MutableList<UserDataModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val setting: ImageView = findViewById(R.id.settingIcon)
        setting.setOnClickListener {

            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)

        }

        val cardStackView: CardStackView = findViewById(R.id.cardStackView)

        manager = CardStackLayoutManager(baseContext, object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {

            }

            override fun onCardRewound() {

            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {
            }

            override fun onCardDisappeared(view: View?, position: Int) {
            }

        })

        cardStackAdapter = CardStackAdapter(baseContext, userDataList)
        cardStackView.layoutManager = manager
        cardStackView.adapter = cardStackAdapter

        getUserDataList()

    }

    private fun getUserDataList() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    val user = dataModel.getValue(UserDataModel::class.java)
                    userDataList.add(user!!)

                }
                cardStackAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

            }
        }
        FirebaseRef.userInfoRef.addValueEventListener(postListener)

    }
}