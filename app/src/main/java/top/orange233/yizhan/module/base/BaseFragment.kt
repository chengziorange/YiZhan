package top.orange233.yizhan.module.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(getLayout(), container, false)
        return rootView
    }

    protected abstract fun getLayout(): Int

    protected abstract fun initView()

    protected abstract fun initEvent()

    protected fun <T : View> findViewById(@IdRes id: Int): T? {
        return rootView.findViewById(id)
    }
}