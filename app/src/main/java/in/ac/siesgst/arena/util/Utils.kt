package `in`.ac.siesgst.arena.util

import `in`.ac.siesgst.arena.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{

        fun getFormattedDate(isoDate: String) : String {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            try {
                val date = format.parse(isoDate)
                return date.toString()
            } catch (e: ParseException) {
                return "not found"
            }
        }

        fun getTitle(rating: Int) : String {
            var title = "Legend"
            if ( rating < 1000 ) {
                title = "Novice"
            } else if (rating < 1200) {
                title = "Beginner"
            } else if (rating < 1400) {
                title = "Intermediate"
            } else if (rating < 1600) {
                title = "Advanced"
            } else if (rating < 1800) {
                title = "Expert"
            } else if (rating < 2000) {
                title = "Master"
            }
            return title
        }

        fun getColorCode(rating: Int) : Int {
            var color = R.color.legendColor
            if ( rating < 1000 ) {
                color = R.color.noviceColor
            } else if (rating < 1200) {
                color = R.color.beginnerColor
            } else if (rating < 1400) {
                color = R.color.intermediateColor
            } else if (rating < 1600) {
                color = R.color.advancedColor
            } else if (rating < 1800) {
                color = R.color.expertColor
            } else if (rating < 2000) {
                color = R.color.masterColor
            }
            return color
        }
    }
}