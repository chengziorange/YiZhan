package top.orange233.yizhan.module.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initView()
        initEvent()
    }

    protected abstract fun getLayout(): Int

    protected abstract fun initView()

    protected abstract fun initEvent()
}