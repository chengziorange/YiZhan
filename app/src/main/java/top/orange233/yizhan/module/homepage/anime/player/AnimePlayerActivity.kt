package top.orange233.yizhan.module.homepage.anime.player

import android.content.res.Configuration
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ktx.immersionBar
import com.orhanobut.logger.Logger
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_anime_player.*
import top.orange233.yizhan.R
import top.orange233.yizhan.common.network.AnimeWithUrl
import top.orange233.yizhan.data.AnimeEpisode
import top.orange233.yizhan.module.base.BaseActivity

class AnimePlayerActivity : BaseActivity(), AnimePlayerContract.View {
    companion object {
        const val ANIME_EPISODE_URL = "anime_episode_url"
    }

    private lateinit var presenter: AnimePlayerPresenter

    private var isPlay: Boolean? = null
    private var isPause: Boolean? = null
    private var orientationUtils: OrientationUtils? = null

    override fun getLayout(): Int = R.layout.activity_anime_player

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            transparentStatusBar()
            statusBarDarkFont(true)
//            hideStatusBar()
        }

        presenter = AnimePlayerPresenter(this)
        presenter.start()

//        Glide.with(this).load(R.drawable.ic_launcher_foreground).into(iv_anime_image)

        presenter.fetchEpisodeInfo(intent.getStringExtra(ANIME_EPISODE_URL))
    }

    override fun initEvent() {
        // TODO("Not yet implemented")
    }

    override fun loadEpisode(animeWithUrl: AnimeWithUrl) {
        initPlayer()
        video_player.backButton.setOnClickListener { onBackPressed() }
        video_player.setUp(
            animeWithUrl.list?.get(0)?.m3u8url,
            false,
            animeWithUrl.list?.get(0)?.num
        )
        animeWithUrl.list?.let { createEpisodeList(it) }
//        Glide.with(this).load(animeWithUrl.date.searchResultCover).into(iv_anime_image)
//        Logger.e("anime name is ${animeWithUrl.date.searchResultName}")
//        Logger.e("anime info is ${animeWithUrl.date.searchResultIntroduce}")
//        tv_anime_name.text = animeWithUrl.date.searchResultName
//        tv_anime_info.text = animeWithUrl.date.searchResultIntroduce
    }

    private fun createEpisodeList(animeEpisodeList: List<AnimeEpisode>) {
        animeEpisodeList.forEach { episode ->
            val relativeLayout =
                View.inflate(this, R.layout.flex_item_anime_episode, null) as RelativeLayout
            val textView = relativeLayout.findViewById<TextView>(R.id.tv_episode_name)
            textView.text = episode.num
            textView.setOnClickListener {
                video_player.setUp(
                    episode.m3u8url,
                    false,
                    episode.num
                )
            }
            episode_box.addView(relativeLayout)
        }
    }

    private fun initPlayer() {
        val orientationUtils = OrientationUtils(this, video_player)
        orientationUtils.isEnable = false

        val playerOptions = GSYVideoOptionBuilder()
        playerOptions.setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setAutoFullWithSize(true)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
//            .setUrl("https://iqiyi.cdn9-okzy.com/20200201/5726_7fae66e4/index.m3u8")
//            .setCacheWithPlay(false)
//            .setVideoTitle("测试视频")
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                    super.onQuitFullscreen(url, objects)
                    if (orientationUtils != null) {
                        orientationUtils.backToProtVideo()
                    }
                }

                override fun onPrepared(url: String?, vararg objects: Any?) {
                    super.onPrepared(url, objects)
                    orientationUtils.isEnable = true
                    isPlay = true
                    Logger.e("isPlay: $isPlay")
                }
            })
            .setLockClickListener { view, lock ->
                if (orientationUtils != null) {
                    orientationUtils.isEnable = !lock
                }
            }
            .build(video_player)

        video_player.fullscreenButton.setOnClickListener { view ->
            orientationUtils.resolveByClick()
            video_player.startWindowFullscreen(this, true, true)
        }
    }

    override fun onBackPressed() {
        orientationUtils?.backToProtVideo()
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        video_player.currentPlayer.onVideoPause()
        super.onPause()
        isPause = true
        Logger.e("isPause: $isPause")
    }

    override fun onResume() {
        video_player.currentPlayer.onVideoResume(false)
        super.onResume()
        isPause = false
        Logger.e("isPause: $isPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.e("isPlay: $isPlay")
        if (isPlay!!) {
            video_player.currentPlayer.release()
        }
        orientationUtils?.releaseListener()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Logger.e("isPlay: $isPlay")
        Logger.e("isPause: $isPause")
        if (isPlay!! && !isPause!!) {
            video_player.onConfigurationChanged(this, newConfig, orientationUtils, true, true)
        }
    }
}