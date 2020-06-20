package top.orange233.yizhan.module.homepage.news

import com.bumptech.glide.Glide
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_news_reader.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseActivity

class NewsReaderActivity : BaseActivity() {
    companion object {
        const val NEWS_ID = "news_id"
    }

    private var newsId: Int? = null

    override fun getLayout(): Int = R.layout.activity_news_reader

    override fun initView() {
        immersionBar {
            statusBarDarkFont(true)
            fitsSystemWindows(true)
        }

        newsId = intent.getIntExtra(NEWS_ID, -1)

        // TODO replace the load url
        Glide.with(this).load(R.drawable.ic_launcher_foreground)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(app_bar_image)
    }

    override fun initEvent() {
        //TODO("Not yet implemented")
    }
}