package top.orange233.yizhan.module.homepage.anime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.rv_item_anime.view.*
import top.orange233.yizhan.R
import top.orange233.yizhan.data.Anime

class AnimeAdapter(
    var animeList: MutableList<Anime>? = null,
    var clickListener: ItemViewOnClickListener? = null
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (animeList == null) {
            0
        } else animeList!!.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        animeList?.apply { holder.bind(this[position], position) }
    }

    inner class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(anime: Anime, position: Int) = with(itemView) {
            tv_anime_title.text = anime.title
            Glide.with(itemView).load(anime.cover?.replace("http://", "https://"))
                .transform(CenterCrop())
                .into(iv_anime_image)
            itemView.setOnClickListener {
                Logger.d("clicked anime in adapter")
                clickListener?.onClickAnime(anime) }
        }
    }

    interface ItemViewOnClickListener {
        fun onClickAnime(anime: Anime)
    }
}