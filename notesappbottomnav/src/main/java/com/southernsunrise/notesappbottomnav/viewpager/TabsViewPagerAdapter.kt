package com.southernsunrise.notesappbottomnav.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.southernsunrise.notesappbottomnav.fragments.draw.DrawTabFragment
import com.southernsunrise.notesappbottomnav.fragments.notes.NotesTabFragment
import com.southernsunrise.notesappbottomnav.fragments.settings.SettingsTabFragment
import com.southernsunrise.notesappbottomnav.fragments.starreds.StarredTabFragment

class TabsViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private var fragmentList: List<Fragment> = listOf()

    fun submitFragmentList(fragmentList: List<Fragment>) {
        this.fragmentList = fragmentList
    }


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}