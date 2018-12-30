package com.stamenkovski.recyclerindicators

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arindicatorview.ARIndicatorView
import com.stamenkovski.recyclerindicators.ListView.ListViewActivity
import com.stamenkovski.recyclerindicators.ViewPager.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var arIndicatorView: ARIndicatorView

    var didChangeIndicatorShape: Boolean = false

    private lateinit var buttonScrubbing: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = recycler
        arIndicatorView = ar_indicator
        buttonScrubbing = button

        this.supportActionBar?.apply {
            title = "RecyclerView"
        }

        recyclerView.adapter = Adapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        arIndicatorView.attachTo(recyclerView, true)

    }

    fun startViewPager(view: View) {
        startActivity(Intent(this, ViewPagerActivity::class.java))
    }

    fun startListView(view: View) {
        startActivity(Intent(this, ListViewActivity::class.java))
    }

    fun changeIndicatorShape(view: View) {

        val an: RotateAnimation = if (!didChangeIndicatorShape) {
            RotateAnimation(
                0.0f, 180.0f, (arIndicatorView.width / 2).toFloat(),
                (arIndicatorView.height / 2).toFloat()
            )
        } else {
            RotateAnimation(
                180.0f, 0f, (arIndicatorView.width / 2).toFloat(),
                (arIndicatorView.height / 2).toFloat()
            )
        }
        this.didChangeIndicatorShape = !this.didChangeIndicatorShape

        an.duration = 1000
        an.repeatCount = 0
        an.repeatMode = Animation.REVERSE
        an.fillAfter = false
        an.isFillEnabled = true

        an.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) = if (didChangeIndicatorShape) {
                arIndicatorView.rotation = 0f
                arIndicatorView.indicatorSize = 50
                arIndicatorView.indicatorShape = R.drawable.my_shape
                arIndicatorView.selectionColor = ContextCompat.getColor(this@MainActivity, R.color.colorPrimary)
                arIndicatorView.indicatorColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
                arIndicatorView.isScrubbingEnabled = true
                arIndicatorView.isShouldAnimateOnScrubbing = true
                arIndicatorView.indicatorAnimation = R.anim.fade_in
            } else {

                arIndicatorView.indicatorSize = 20
                arIndicatorView.indicatorShape = 0
                arIndicatorView.selectionColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
                arIndicatorView.indicatorColor = ContextCompat.getColor(this@MainActivity, R.color.colorPrimary)
                arIndicatorView.selectedPosition = 5
                arIndicatorView.isScrubbingEnabled = false
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        arIndicatorView.startAnimation(an)

    }

    fun isScrubbingOn(button: View) {
        this.arIndicatorView.isScrubbingEnabled = !this.arIndicatorView.isScrubbingEnabled
        buttonScrubbing.text = "Scrubbing ${this.arIndicatorView.isScrubbingEnabled}"
    }

    fun animateScrubbing(view: View) {
        this.arIndicatorView.isShouldAnimateOnScrubbing = !this.arIndicatorView.isShouldAnimateOnScrubbing
       /* if (!this.arIndicatorView.isShouldAnimateOnScrubbing) {
            buttonAnimation.text = "Animation off"
        } else {
            buttonAnimation.text = "Animation on"
        }*/
    }
}
