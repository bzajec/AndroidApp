package hr.algebra.football_projekt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.football_projekt.model.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemPagerAdapter(private val items: MutableList<Item>, private val context: Context) : RecyclerView.Adapter<ItemPagerAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem : ImageView = itemView.findViewById(R.id.ivItem)
        private val tvDate : TextView = itemView.findViewById(R.id.tvDate)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription : TextView = itemView.findViewById(R.id.tvDescription)
        private val tvGenre : TextView = itemView.findViewById(R.id.tvGenre)
        private val tvPlatform : TextView = itemView.findViewById(R.id.tvPlatform)
        private val tvPublisher : TextView = itemView.findViewById(R.id.tvPublisher)
        private val tvDeveloper : TextView = itemView.findViewById(R.id.tvDeveloper)
        private val tvGameUrl : TextView = itemView.findViewById(R.id.tvGameUrl)
        private val tvMoreUrl : TextView = itemView.findViewById(R.id.tvMoreUrl)

        fun bind(item: Item){

            Picasso.get()
                .load(File(item.picturePath))
                .error(R.drawable.controller)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            tvDate.text = item.date
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvGenre.text = item.genre
            tvPlatform.text = item.platform
            tvPublisher.text = item.publisher
            tvDeveloper.text = item.developer
            tvGameUrl.text = item.gameUrl
            tvMoreUrl.text = item.moreUrl

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pager, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}



