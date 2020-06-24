package top.orange233.yizhan.module.profile

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.fragment_profile.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseFragment

class ProfileFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_profile

    override fun initView() {
        immersionBar {
            statusBarColor(R.color.colorPrimary)
            statusBarDarkFont(false)
        }

        Glide.with(this)
            .load("https://i.loli.net/2020/06/20/jFJ9YgqMGyfRbNH.png")
            .transform(CenterCrop(), CircleCrop())
            .into(profile_avator)
    }

    override fun initEvent() {
    }
}