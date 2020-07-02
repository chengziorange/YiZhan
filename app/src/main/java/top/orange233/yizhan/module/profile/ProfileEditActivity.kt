package top.orange233.yizhan.module.profile

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Handler
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.gyf.immersionbar.ktx.immersionBar
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_profile_edit.*
import top.orange233.yizhan.R
import top.orange233.yizhan.common.network.Profile
import top.orange233.yizhan.module.base.BaseActivity

class ProfileEditActivity : BaseActivity(), ProfileEditContract.View {
    private val REQUEST_PICK_PHOTO = 1
    private val REQUEST_FILE_PERMISSION = 2
    private var avatarUri: Uri? = null
    private var avatarPath: String? = null

    private lateinit var presenter: ProfileEditContract.Presenter

    override fun getLayout(): Int = R.layout.activity_profile_edit

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarDarkFont(false)
        }

        presenter = ProfileEditPresenter(this)
        presenter.start()

        Handler().postDelayed({ requestFileReadPermission() }, 1000)
    }

    override fun initEvent() {
        toolbar.setNavigationOnClickListener { finish() }

        btn_cancel.setOnClickListener { onBackPressed() }

        btn_pick_avatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_PICK_PHOTO)
        }

        btn_submit.setOnClickListener {
            Logger.d("submit button onclick")
            val userName = profile_edit_username_edittext.text.toString()
            val password = profile_edit_password_edittext.text.toString()
            val gender = when (radioGroup.checkedRadioButtonId) {
                R.id.rbtn_woman -> "女"
                else -> "男"
            }
            presenter.changeProfile(userName, password, avatarPath, gender)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            Logger.d("checked radioId is $checkedId")
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
            Snackbar.make(iv_avatar, "成功获取读取图片权限", Snackbar.LENGTH_SHORT).show()
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
                    Snackbar.make(iv_avatar, "成功获取读取图片权限", Snackbar.LENGTH_SHORT).show()
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
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            avatarUri = data?.data
            avatarPath = avatarUri?.path?.replace("/raw//storage", "/storage")
            Logger.d("returned url path is $avatarPath")
        }
    }

    override fun onResume() {
        super.onResume()
        if (avatarUri != null) {
            Glide.with(this).load(avatarUri)
                .into(iv_avatar)
            avatarUri = null
        }
    }

    override fun autoCompleteProfile(profile: Profile) {
        profile_edit_username_edittext.setText(profile.userName)

        Glide.with(this).load(profile.avatarUrl?.replace("http://", "https://"))
            .into(iv_avatar)

        when (profile.gender) {
            "女" -> rbtn_woman.isChecked = true
            else -> rbtn_man.isChecked = true
        }
    }

    override fun autoCompleteProfileFail() {
        Snackbar.make(iv_avatar, "更新个人资料失败", Snackbar.LENGTH_SHORT).show()
    }

    override fun getViewContext(): Context = this

    override fun changeProfileSuccess() {
        Snackbar.make(iv_avatar, "修改成功！", Snackbar.LENGTH_SHORT).show()
        finish()
    }
}