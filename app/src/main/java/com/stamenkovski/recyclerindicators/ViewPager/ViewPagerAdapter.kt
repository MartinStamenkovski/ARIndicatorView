package com.stamenkovski.recyclerindicators.ViewPager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stamenkovski.recyclerindicators.R
import kotlinx.android.synthetic.main.items_view_pager.view.*

class ViewPagerAdapter(val context: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val viewGroup = layoutInflater.inflate(R.layout.items_view_pager, container, false)

        val image = viewGroup.view_pager_image
        container.addView(viewGroup)
        return viewGroup
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {

        return p0 == p1
    }

    override fun getCount(): Int {
        return 3
    }
}