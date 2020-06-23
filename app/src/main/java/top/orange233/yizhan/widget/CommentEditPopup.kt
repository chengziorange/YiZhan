package top.orange233.yizhan.widget

import android.content.Context
import android.view.View
import android.widget.Button
import razerdp.basepopup.BasePopupWindow
import top.orange233.yizhan.R

class CommentEditPopup(context: Context, private val clickListener: CommitButtonOnClickListener) :
    BasePopupWindow(context) {

    init {
        setAdjustInputMethod(true)
        setAutoShowInputMethod(findViewById(R.id.et_comment_content), true)
    }

    override fun onCreateContentView(): View = createPopupById(R.layout.popup_comment_edit)

    override fun onViewCreated(contentView: View) {
        super.onViewCreated(contentView)
        initEvent()
    }

    private fun initEvent() {
        val commitButton = findViewById<Button>(R.id.commit_button)
        commitButton.setOnClickListener { clickListener.onCommitButtonClick(contentView) }
    }

    interface CommitButtonOnClickListener {
        fun onCommitButtonClick(contentView: View)
    }
}