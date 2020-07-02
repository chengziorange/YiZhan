package top.orange233.yizhan.module.profile

import android.content.Context
import top.orange233.yizhan.common.network.Profile
import top.orange233.yizhan.module.base.BasePresenter

interface ProfileEditContract {
    interface View {
        fun getViewContext(): Context
        fun autoCompleteProfile(profile: Profile)
        fun autoCompleteProfileFail()
        fun changeProfileSuccess()
    }

    interface Presenter : BasePresenter {
        fun getProfile()
        fun changeProfile(
            userName: String?,
            password: String?,
            avatarPath: String?,
            gender: String?
        )
    }
}