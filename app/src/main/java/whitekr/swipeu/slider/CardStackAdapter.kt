package whitekr.swipeu.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import whitekr.swipeu.R

class CardStackAdapter(
    val context: Context,
    val items: List<String>
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardStackAdapter.ViewHolder, position: Int) =
        holder.binding(items[position])

    override fun getItemCount(): Int = items.count()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun binding(data: String) {
            
        }

    }
}