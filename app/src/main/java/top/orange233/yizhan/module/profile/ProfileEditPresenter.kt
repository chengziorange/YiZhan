package top.orange233.yizhan.module.profile

import android.util.Base64
import android.util.Log
import com.orhanobut.logger.Logger
import top.orange233.yizhan.common.repository.UserRepository
import top.orange233.yizhan.util.Preference
import top.zibin.luban.Luban
import java.io.FileInputStream
import java.lang.Exception

class ProfileEditPresenter(private val view: ProfileEditContract.View) :
    ProfileEditContract.Presenter {

    override fun start() {
        // TODO("Not yet implemented")
    }

    override fun getProfile() {
        UserRepository.getInstance().getProfile()
            .subscribe({
                Logger.d(it)
                when (it.status) {
                    201 -> {
                        view.autoCompleteProfile(it)
                    }
                    401 -> {
                        view.autoCompleteProfileFail()
                    }
                }
            }, {
                it.printStackTrace()
            })
    }

    override fun changeProfile(
        userName: String?,
        password: String?,
        avatarPath: String?,
        gender: String?
    ) {
        try {
            val file =
                Luban.with(view.getViewContext()).load(avatarPath).filter { true }.ignoreBy(500)
                    .get()[0]
            val byteArray = ByteArray(file.length().toInt())
            val fis = FileInputStream(file)
            fis.read(byteArray)
            fis.close()
            val avatarBase64 =
                Base64.encodeToString(byteArray, 0, file.length().toInt(), Base64.DEFAULT)
            Log.d("TAG", avatarBase64)
            Preference.instance.putValue(Preference.KEY_AVATAR_PATH, avatarPath)
            UserRepository.getInstance().changeProfile(userName, password, avatarBase64, gender)
                .subscribe({
                    when (it.status) {
                        201 -> view.changeProfileSuccess()
                    }
                }, { it.printStackTrace() })
        } catch (e: Exception) {
        }
    }
}