package whitekr.swipeu.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

        val name: TextView = itemView.findViewById(R.id.itemName)
        val age: TextView = itemView.findViewById(R.id.itemAge)
        val city: TextView = itemView.findViewById(R.id.itemCity)

        fun binding(data: UserDataModel) {

            name.text = data.name
            age.text = data.age
            city.text = data.city

        }
    }
}