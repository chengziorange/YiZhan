package top.orange233.yizhan.module.homepage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import top.orange233.yizhan.module.homepage.anime.AnimeFragment
import top.orange233.yizhan.module.homepage.comic.ComicFragment
import top.orange233.yizhan.module.homepage.news.NewsFragment

class HomepageAdapter(f: Fragment) : FragmentStateAdapter(f) {
    private val homepageFragments: List<Fragment> =
        listOf(NewsFragment(), ComicFragment(), AnimeFragment())

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> homepageFragments[0]
            1 -> homepageFragments[1]
            2 -> homepageFragments[2]
            else -> throw Throwable("Unknown homepage category.")
        }
    }
}