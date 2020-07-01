package top.orange233.yizhan.module.homepage.news.reader

import com.google.android.material.snackbar.Snackbar
import com.orhanobut.logger.Logger
import top.orange233.yizhan.common.repository.UserRepository
import top.orange233.yizhan.data.NewsComment

class NewsReaderPresenter(private val view: NewsReaderContract.View) :
    NewsReaderContract.Presenter {

    private var comments: MutableList<NewsComment>? = null
    private var commentAdapter: NewsCommentAdapter? = null

    override fun refreshPage() {
        UserRepository.getInstance().getNewsComment(view.getNewsId())
            .subscribe({
                Logger.d(it)
                when (it.status) {
                    200 -> {
                        comments?.clear()
                        comments?.addAll(it.comments)
                    }
                }
                view.finishRefreshPage()
            }, { it.printStackTrace() })
    }

    override fun loadMoreComment() {
        UserRepository.getInstance().getNewsComment(view.getNewsId())
            .subscribe({
                Logger.d(it)
                when (it.status) {
                    200 -> {
                        comments?.clear()
                        comments?.addAll(it.comments)
                    }
                }
                view.finishLoadMore()
            }, { it.printStackTrace() })
    }

    override fun start() {
        if (comments == null) {
            comments = mutableListOf()
        }
        if (commentAdapter == null) {
            commentAdapter = NewsCommentAdapter(comments)
        }
    }

    override fun addComment(content: String) {
        UserRepository.getInstance().addNewsComment(view.getNewsId(), content)
            .subscribe({
                when (it.status) {
                    401 -> view.commentFail()
                    200 -> view.commentSuccess()
                }
            }, {
                it.printStackTrace()
            })
    }

    override fun getAdapter(): NewsCommentAdapter = commentAdapter!!
}