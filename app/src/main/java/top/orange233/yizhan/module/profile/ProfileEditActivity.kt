package top.orange233.yizhan.module.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.gyf.immersionbar.ktx.immersionBar
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_profile_edit.*
import top.orange233.yizhan.R
import top.orange233.yizhan.common.repository.UserRepository
import top.orange233.yizhan.module.base.BaseActivity
import top.orange233.yizhan.module.home.login.LoginActivity
import top.orange233.yizhan.util.Preference
import top.zibin.luban.Luban
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.lang.Exception

class ProfileEditActivity : BaseActivity() {
    private val REQUEST_PICK_PHOTO = 1
    private val REQUEST_FILE_PERMISSION = 2
    private var avatarUri: Uri? = null
    private var avatarPath: String? = null

    override fun getLayout(): Int = R.layout.activity_profile_edit

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarDarkFont(false)
        }

        UserRepository.getInstance().getProfile()
            .subscribe({
                Logger.d(it)
                when (it.status) {
                    201 -> {
                        profile_edit_username_edittext.setText(it.userName)

                        Glide.with(this).load(it.avatarUrl?.replace("http://", "https://"))
                            .into(iv_avatar)

                        when (it.gender) {
                            "女" -> rbtn_woman.isChecked = true
                            else -> rbtn_man.isChecked = true
                        }
                    }
                    401 -> {
                        Preference.instance.putValue(Preference.KEY_IS_LOGGED_IN, false)
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }, {})

        iv_avatar.postDelayed({ requestFileReadPermission() }, 1000)
    }

    override fun initEvent() {
        toolbar.setNavigationOnClickListener { finish() }

        btn_cancel.setOnClickListener { onBackPressed() }

        btn_pick_avatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_PICK_PHOTO)
        }
    }

    private fun requestFileReadPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_CONTACTS
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_FILE_PERMISSION
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            initSubmitButton()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Logger.d("onRequestPermissionResult invoked")
        when (requestCode) {
            REQUEST_FILE_PERMISSION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    initSubmitButton()
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            avatarUri = data?.data
            avatarPath = avatarUri?.path?.replace("/raw//storage", "/storage")
            Logger.d("returned url path is $avatarPath")
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        if (avatarUri != null) {
            Glide.with(this).load(avatarUri)
                .into(iv_avatar)
            avatarUri = null
        }
        super.onResume()
    }

    private fun initSubmitButton() {
        btn_submit.setOnClickListener {
            val userName = profile_edit_username_edittext.text.toString()
            val password = profile_edit_password_edittext.text.toString()

            Logger.d(avatarPath)

//            val file = File(avatarPath)
            // TODO dirty code
            var avatarBase64: String? = null
            try {
                val file = Luban.with(this).load(avatarPath).filter { true }.ignoreBy(100).get()[0]
                val buf = BufferedInputStream(FileInputStream(file))
                val byteArray = ByteArray(file.length().toInt())
                buf.read(byteArray, 0, byteArray.size)
                buf.close()
                avatarBase64 =
                    "data:image/jpg;base64," + Base64.encodeToString(byteArray, Base64.DEFAULT)
            } catch (e: Exception) {
            }

            val gender = when (radioGroup.checkedRadioButtonId) {
                1 -> "女"
                else -> "男"
            }
            UserRepository.getInstance().changeProfile(
                if (userName == "") null else userName,
                if (password == "") null else password,
                avatarBase64,
                gender
            ).subscribe({
                when (it.status) {
                    201 -> {
                        Snackbar.make(iv_avatar, "修改成功！", Snackbar.LENGTH_SHORT).show()
                        Preference.instance.putValue(Preference.KEY_PROFILE_CHANGED, true)
                    }
                    else -> Logger.d(it)
                }
            }, { it.printStackTrace() })
        }
    }
}