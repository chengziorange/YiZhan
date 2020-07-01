package top.orange233.yizhan.common.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import top.orange233.yizhan.common.network.UserService

class UserRepository private constructor(private val userService: UserService) {

    companion object {
        private val INSTANCE: UserRepository = UserRepository(UserService.create())
        fun getInstance() = INSTANCE
    }

    fun login(userId: String, password: String) =
        userService.login(userId, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun register(email: String, password: String) =
        userService.register(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getProfile() =
        userService.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun changeProfile(userName: String?, password: String?, avatarBase64: String?, gender: String?) =
        userService.changeProfile(userName, password, avatarBase64, gender)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}