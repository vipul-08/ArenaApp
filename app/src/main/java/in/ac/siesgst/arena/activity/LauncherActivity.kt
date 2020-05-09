package `in`.ac.siesgst.arena.activity

import `in`.ac.siesgst.arena.R
import `in`.ac.siesgst.arena.adapter.IntroSlideAdapter
import `in`.ac.siesgst.arena.util.IntroSlide
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {

    private val introSliderAdapter = IntroSlideAdapter(
        listOf(
            IntroSlide(
                "reserved",
                "reserved",
                R.drawable.arena_image
            ),
            IntroSlide(
                "Ninad Chavan",
                "A place to explore the incredible world of coding and a team with which solving any crazy problem becomes possible.",
                R.drawable.ninad
            ),
            IntroSlide(
                "Rahul Sawantdesai",
                "Fun isn't something one considers when creating problems. But this.... does put a smile on my face.",
                R.drawable.rahul
            ),
            IntroSlide(
                "Shambhavi Sudarshan",
                "All of us learn new things but when we actually implement it and do mistakes is when we learn more and more. CodeChef Chapter team has offered me a positive environment where everyone is working with passion towards perfecting a work.",
                R.drawable.shambhavi
            ),
            IntroSlide(
                "Cibin Chandrashekhar",
                "A battleground where you contend to conquer, with a lot to Learn, Run and Debug...",
                R.drawable.cibin
            ),
            IntroSlide(
                "Omkar Prabhu",
                "Building the platform helped us solve a lot of technical challenges which we never thought of. I'm always excited to see what's the next problem we'll be solving and the creative solutions which comes with it.",
                R.drawable.omkar
            ),
            IntroSlide(
                "Harsh Agrawal",
                "Every puzzle has a solution , every question has an answer, the real puzzle lies in speed, and the real question is in your head. Can I solve this?",
                R.drawable.harsh
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        introSlider.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSlider.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        buttonNext.setOnClickListener {
            if (introSlider.currentItem + 1 < introSliderAdapter.itemCount) {
                introSlider.currentItem += 1
            } else {
                Intent(applicationContext, UsernameActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        textSkipIntro.setOnClickListener {
            Intent(applicationContext, UsernameActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active))
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active))
            }
        }
    }
}
