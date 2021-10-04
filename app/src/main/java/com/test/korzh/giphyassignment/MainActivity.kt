package com.test.korzh.giphyassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.test.korzh.giphyassignment.databinding.ActivityMainBinding
import com.test.korzh.giphyassignment.ui.adapter.SectionsPagerAdapter
import com.test.korzh.giphyassignment.ui.adapter.TabName

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabLayout: TabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(TabName.getTitle(position))
        }.attach()
    }
}