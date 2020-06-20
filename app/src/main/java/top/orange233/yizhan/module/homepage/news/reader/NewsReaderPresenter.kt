package top.orange233.yizhan.module.homepage.news.reader

import top.orange233.yizhan.data.NewsComment

class NewsReaderPresenter(private val view: NewsReaderContract.View) :
    NewsReaderContract.Presenter {

    private var comments: MutableList<NewsComment>? = null
    private var commentAdapter: NewsCommentAdapter? = null

    override fun refreshPage() {
        // TODO("Not yet implemented")
    }

    override fun loadMoreComment() {
        // TODO("Not yet implemented")
    }

    override fun start() {
        if (comments == null) {
            comments = mutableListOf(
                NewsComment(1, "123", "张小马", 1592654491000, "Interesting"),
                NewsComment(2, "456", "李四", 1592568090000, "Interesting~~")
            )
        }
        if (commentAdapter == null) {
            commentAdapter = NewsCommentAdapter(comments)
        }
    }

    override fun getAdapter(): NewsCommentAdapter = commentAdapter!!
}