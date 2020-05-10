package `in`.ac.siesgst.arena.adapter

import `in`.ac.siesgst.arena.R
import `in`.ac.siesgst.arena.model.IntroSlide
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class IntroSlideAdapter(private val introSlides: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }


    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<CircleImageView>(R.id.imageSlideIcon)
        private val welcomeText = view.findViewById<TextView>(R.id.welcomeText)

        fun bind(slide: IntroSlide) {
            if (slide.title == "reserved") {
                textTitle.visibility = GONE
                textDescription.visibility = GONE
                welcomeText.visibility = VISIBLE
                imageIcon.setImageResource(slide.icon)
            } else {
                textTitle.visibility = VISIBLE
                textDescription.visibility = VISIBLE
                welcomeText.visibility = GONE
                textTitle.text = "~ ${slide.title}"
                textDescription.text = slide.description
                imageIcon.setImageResource(slide.icon)
            }
        }
    }
}