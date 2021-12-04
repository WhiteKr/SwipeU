package whitekr.swipeu.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import whitekr.swipeu.R
import whitekr.swipeu.auth.UserDataModel

class CardStackAdapter(
    val context: Context,
    val items: List<UserDataModel>
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardStackAdapter.ViewHolder, position: Int) =
        holder.binding(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val image: ImageView = itemView.findViewById(R.id.profileImageArea)
        val name: TextView = itemView.findViewById(R.id.itemName)
        val age: TextView = itemView.findViewById(R.id.itemAge)
        val city: TextView = itemView.findViewById(R.id.itemCity)

        fun binding(data: UserDataModel) {

            val storageRef = Firebase.storage.reference.child("${data.uid}.png")
            storageRef.downloadUrl.addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    Glide.with(context)
                        .load(task.result)
                        .into(image)

                }
            }

            name.text = data.name
            age.text = data.age
            city.text = data.city

        }
    }
}