package `in`.ac.siesgst.arena.adapter

import `in`.ac.siesgst.arena.R
import `in`.ac.siesgst.arena.model.Problem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProblemsAdapter(private val problems: List<Problem>) : RecyclerView.Adapter<ProblemsAdapter.ProblemViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        return ProblemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_problem_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return problems.size
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.bind(problems[position])
    }

    inner class ProblemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleText = view.findViewById<TextView>(R.id.problemTitle)
        val problemCodeText = view.findViewById<TextView>(R.id.problemCode)
        val problemScoreText = view.findViewById<TextView>(R.id.problemScore)

        fun bind(problem: Problem) {
            titleText.text = problem.name
            problemCodeText.text = problem.code
            problemScoreText.text = problem.points.toString()
        }
    }
}