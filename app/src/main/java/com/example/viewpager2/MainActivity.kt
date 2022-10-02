package com.example.viewpager2

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback


class MainActivity : AppCompatActivity() {

    private var dotsCount = 3 //No of tabs or images
    private lateinit var dots: Array<ImageView?>
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = IntroAdapter(this)
        val pager = findViewById<ViewPager2>(R.id.pager)
        linearLayout = findViewById(R.id.viewPagerCountDots)

        setPageViewIndicator()
        pager.adapter = adapter
        pager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                for (i in 0 until dotsCount) {
                    dots[i]!!.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.item_unselected))
                }

                dots[position]!!.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.item_selected))

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

    }


    private fun setPageViewIndicator() {
        Log.d("###setPageViewIndicator", " : called")
        dotsCount = 3
        dots = arrayOfNulls(dotsCount)
        for (i in 0 until dotsCount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.item_unselected))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
//            dots[i]!!.setOnTouchListener { v, event ->
//                mViewPager.setCurrentItem(i)
//                true
//            }
            linearLayout.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.item_selected))
    }
}