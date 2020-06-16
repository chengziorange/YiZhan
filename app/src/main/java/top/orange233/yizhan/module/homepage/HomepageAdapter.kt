package top.orange233.yizhan.module.homepage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import top.orange233.yizhan.module.homepage.news.NewsFragment

class HomepageAdapter(f: Fragment) : FragmentStateAdapter(f) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> NewsFragment()
            else -> NewsFragment()
        }
    }
}