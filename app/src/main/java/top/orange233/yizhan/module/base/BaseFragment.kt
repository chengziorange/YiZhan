package top.orange233.yizhan.module.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(getLayout(), container, false)

    protected abstract fun getLayout(): Int

    protected abstract fun initView()

    protected abstract fun initEvent()
}