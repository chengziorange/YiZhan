package top.orange233.yizhan.module.homepage.anime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.rv_item_anime_search.view.*
import top.orange233.yizhan.R
import top.orange233.yizhan.data.Anime

class AnimeSearchAdapter(
    var animeList: MutableList<Anime>? = null,
    var clickListener: ItemViewOnClickListener? = null
) : RecyclerView.Adapter<AnimeSearchAdapter.AnimeSearchViewHolder>() {

    inner class AnimeSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(anime: Anime, position: Int) = with(itemView) {
            tv_anime_name.text = anime.searchResultName
            tv_update_date.text = anime.searchResultTime
            Glide.with(itemView).load(anime.cover?.replace("http://", "https://"))
                .transform(CenterCrop())
                .into(iv_anime_image)
            itemView.setOnClickListener { clickListener?.onClickAnime(anime) }
        }
    }

    interface ItemViewOnClickListener {
        fun onClickAnime(anime: Anime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_anime_search, parent, false)
        return AnimeSearchViewHolder(view);
    }

    override fun getItemCount(): Int {
        return if (animeList == null) {
            0
        } else animeList!!.size
    }

    override fun onBindViewHolder(holder: AnimeSearchViewHolder, position: Int) {
        animeList?.apply { holder.bind(this[position], position) }
    }
}