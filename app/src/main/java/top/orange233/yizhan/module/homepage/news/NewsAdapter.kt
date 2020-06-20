package top.orange233.yizhan.module.homepage.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_item_news.view.*
import top.orange233.yizhan.R
import top.orange233.yizhan.data.News

class NewsAdapter(
    var newsList: MutableList<News>? = null,
    var clickListener: ItemViewOnClickListener? = null
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (newsList == null) {
            0
        } else newsList!!.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        newsList?.apply { holder.bind(this[position], position) }
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(
        view
    ) {
        fun bind(news: News, position: Int) = with(itemView) {
            news_title.text = news.title
            Glide.with(itemView).load(news.smallImageUrl?.get(0)).into(news_image)
            itemView.setOnClickListener { clickListener?.onClickNews(news) }
        }
    }

    interface ItemViewOnClickListener {
        fun onClickNews(news: News)
    }
}
