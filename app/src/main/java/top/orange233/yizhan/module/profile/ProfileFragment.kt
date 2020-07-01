package top.orange233.yizhan.module.profile

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.gyf.immersionbar.ktx.immersionBar
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_profile.*
import top.orange233.yizhan.R
import top.orange233.yizhan.common.repository.UserRepository
import top.orange233.yizhan.module.base.BaseFragment
import top.orange233.yizhan.module.home.login.LoginActivity
import top.orange233.yizhan.util.Preference

class ProfileFragment : BaseFragment() {
    private var isLoggedIn = Preference.instance.getValue(Preference.KEY_IS_LOGGED_IN, false)

    override fun getLayout(): Int = R.layout.fragment_profile

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
            statusBarDarkFont(false)
        }
    }

    override fun initEvent() {
        profile_avator.setOnClickListener {
            if (!isLoggedIn) {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        change_profile_layout.setOnClickListener {
            val intent = Intent(context, ProfileEditActivity::class.java)
            startActivity(intent)
        }

        if (Preference.instance.getValue(Preference.KEY_IS_LOGGED_IN, false)) {
            btn_logout.setOnClickListener {
                Preference.instance.putValue(Preference.KEY_IS_LOGGED_IN, false)
                Preference.instance.putValue(Preference.KEY_COOKIE_JSESSIONID, "233")
                activity?.onBackPressed()
            }
        } else {
            btn_logout.visibility = View.GONE
        }
    }

    override fun onResume() {
        checkProfileChanges()
        super.onResume()
    }

    private fun checkProfileChanges() {
        Logger.d("checkProfileChanges invoked")
        if (Preference.instance.getValue(Preference.KEY_PROFILE_CHANGED, false)
            || Preference.instance.getValue(Preference.KEY_IS_LOGGED_IN, false)
        ) {
            // TODO dirty code, change to MVP later
            UserRepository.getInstance().getProfile()
                .subscribe({
                    when (it.status) {
                        200 -> {
                            Glide.with(this)
                                .load(it.avatarUrl)
                                .placeholder(R.drawable.ic_account_circle)
                                .transform(CenterCrop(), CircleCrop())
                                .into(profile_avator)

                            tv_profile_user_name.text = it.userName

                            when (it.gender) {
                                "女" -> iv_gender.setImageResource(R.drawable.ic_woman)
                                else -> iv_gender.setImageResource(R.drawable.ic_man)
                            }
                        }
                        else -> Logger.d(it)
                    }
                    Preference.instance.putValue(Preference.KEY_PROFILE_CHANGED, false)
                }, {
                    it.printStackTrace()
                })
            btn_logout.visibility = View.VISIBLE
        }
    }
}