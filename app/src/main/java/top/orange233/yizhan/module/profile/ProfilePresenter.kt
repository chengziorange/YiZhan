package top.orange233.yizhan.module.profile

import com.orhanobut.logger.Logger
import top.orange233.yizhan.common.repository.UserRepository
import top.orange233.yizhan.util.Preference

class ProfilePresenter(private val view: ProfileContract.View) : ProfileContract.Presenter {

    override fun start() {
        //TODO("Not yet implemented")
    }

    override fun fetchProfile() {
        UserRepository.getInstance().getProfile()
            .subscribe({
                Logger.d("fetchProfile result is $it")
                when (it.status) {
                    200 -> {
                        view.updateProfile(it)
                        Preference.instance.putValue(Preference.KEY_IS_LOGGED_IN, true)
                    }
                }
            }, { it.printStackTrace() })
    }
}