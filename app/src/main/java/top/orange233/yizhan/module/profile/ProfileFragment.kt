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
import top.orange233.yizhan.common.network.Profile
import top.orange233.yizhan.module.base.BaseFragment
import top.orange233.yizhan.module.home.login.LoginActivity
import top.orange233.yizhan.util.Preference

class ProfileFragment : BaseFragment(), ProfileContract.View {
    private var isLoggedIn: Boolean = false

    private lateinit var presenter: ProfilePresenter

    override fun getLayout(): Int = R.layout.fragment_profile

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
            statusBarDarkFont(false)
        }

        presenter = ProfilePresenter(this)
        presenter.start()

        tv_profile_user_name.text = "点击登录"
    }

    override fun initEvent() {
        val loginListener = View.OnClickListener {
            if (!isLoggedIn) {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        profile_avator.setOnClickListener(loginListener)
        tv_profile_user_name.setOnClickListener(loginListener)

        change_profile_layout.setOnClickListener {
            if (!isLoggedIn) loginListener else {
                val intent = Intent(context, ProfileEditActivity::class.java)
                startActivity(intent)
            }
        }

        if (isLoggedIn) {
            btn_logout.visibility = View.VISIBLE
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
        super.onResume()
        isLoggedIn = Preference.instance.getValue(Preference.KEY_IS_LOGGED_IN, false)
        Logger.d("isLoggedIn in ProfileFragment is $isLoggedIn")
        if (isLoggedIn) {
            Logger.d("fetchProfile")
            presenter.fetchProfile()
        }
    }

    override fun updateProfile(profile: Profile) {
        val localAvatar = Preference.instance.getValue(Preference.KEY_AVATAR_PATH, "")

        Glide.with(this)
            .load(localAvatar)
            .placeholder(R.drawable.ic_account_circle)
            .transform(CenterCrop(), CircleCrop())
            .into(profile_avator)

        tv_profile_user_name.text = profile.userName

        when (profile.gender) {
            "女" -> iv_gender.setImageResource(R.drawable.ic_woman)
            else -> iv_gender.setImageResource(R.drawable.ic_man)
        }

        if (isLoggedIn) {
            btn_logout.visibility = View.VISIBLE
            btn_logout.setOnClickListener {
                Preference.instance.putValue(Preference.KEY_IS_LOGGED_IN, false)
                Preference.instance.putValue(Preference.KEY_COOKIE_JSESSIONID, "233")
                activity?.onBackPressed()
            }
        } else {
            btn_logout.visibility = View.GONE
        }
    }
}