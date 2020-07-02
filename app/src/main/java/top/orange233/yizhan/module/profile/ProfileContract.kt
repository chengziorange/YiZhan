package top.orange233.yizhan.module.profile

import top.orange233.yizhan.common.network.Profile
import top.orange233.yizhan.module.base.BasePresenter

interface ProfileContract {
    interface View {
        fun updateProfile(profile: Profile)
    }

    interface Presenter : BasePresenter {
        fun fetchProfile()
    }
}