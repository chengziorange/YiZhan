package top.orange233.yizhan.module.homepage.news.reader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kotlinx.android.synthetic.main.rv_item_news_comment.view.*
import top.orange233.yizhan.R
import top.orange233.yizhan.data.NewsComment
import top.orange233.yizhan.util.DateFormatter
import java.util.*

class NewsCommentAdapter(
    var comments: MutableList<NewsComment>?
) : RecyclerView.Adapter<NewsCommentAdapter.NewsCommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_news_comment, parent, false)
        return NewsCommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (comments == null) {
            0
        } else comments!!.size
    }

    override fun onBindViewHolder(holder: NewsCommentViewHolder, position: Int) {
        comments?.apply { holder.bind(this[position], position) }
    }

    inner class NewsCommentViewHolder(viwe: View) : RecyclerView.ViewHolder(viwe) {
        fun bind(comment: NewsComment, position: Int) = with(itemView) {
            Glide.with(itemView)
                .load("https://www.aransw.com/acg/random.php?num=${Random().nextInt(100)}")
                .transform(CenterCrop(), CircleCrop())
                .into(iv_avatar)
            tv_user_name.text = if (comment.username == null) "张三" else comment.username
            tv_comment_content.text = comment.content
            tv_comment_date.text = comment.createTime?.let { DateFormatter.formatCommentDate(it) }
        }
    }
}