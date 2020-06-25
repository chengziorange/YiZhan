package top.orange233.yizhan.module.homepage.anime

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_anime_search.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseActivity

class AnimeSearchActivity : BaseActivity(), AnimeSearchContract.View {
    companion object {
        const val ANIME_NAME = "anime_name"
        const val ANIME_IMAGE = "anime_image"
    }

    private lateinit var presenter: AnimeSearchPresenter

    override fun getLayout(): Int = R.layout.activity_anime_search

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            transparentStatusBar()
            statusBarDarkFont(true)
        }

        presenter = AnimeSearchPresenter(this)
        presenter.start()

        val animeName = intent.getStringExtra(ANIME_NAME)
        presenter.imageUrl = intent.getStringExtra(ANIME_IMAGE)
        presenter.getSearchResult(animeName)

        rv_anime_search_result.adapter = presenter.getAdapter()
        rv_anime_search_result.layoutManager = LinearLayoutManager(this)
    }

    override fun initEvent() {
    }

    override fun getViewContext(): Context = this

    override fun updateList() {
        presenter.getAdapter().notifyDataSetChanged()
        progress_bar.visibility = View.GONE
    }
}