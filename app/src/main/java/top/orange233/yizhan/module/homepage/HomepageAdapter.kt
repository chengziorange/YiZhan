package top.orange233.yizhan.module.homepage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import top.orange233.yizhan.module.homepage.anime.AnimeFragment
import top.orange233.yizhan.module.homepage.comic.ComicFragment
import top.orange233.yizhan.module.homepage.news.NewsFragment

class HomepageAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    //    private val homepageFragments: List<Fragment> =
//        listOf(NewsFragment(), ComicFragment(), AnimeFragment())
    private val homepageFragments: List<Fragment> =
        listOf(NewsFragment(), AnimeFragment())

    override fun getItem(position: Int): Fragment = homepageFragments[position]

    override fun getCount(): Int = homepageFragments.size

    override fun getPageTitle(position: Int): CharSequence? = listOf("资讯", "番剧")[position]
}