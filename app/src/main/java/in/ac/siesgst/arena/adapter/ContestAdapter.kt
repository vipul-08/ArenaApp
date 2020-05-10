package `in`.ac.siesgst.arena.adapter

import `in`.ac.siesgst.arena.R
import `in`.ac.siesgst.arena.fragment.ProblemSetFragment
import `in`.ac.siesgst.arena.model.Contest
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class ContestAdapter(private val contests: List<Contest>, private val manager: FragmentManager): RecyclerView.Adapter<ContestAdapter.ContestViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        return ContestViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_contest_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return contests.size
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        holder.bind(contests[position])
    }

    inner class ContestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleText = view.findViewById<TextView>(R.id.contestTitle)
        private val descriptionText = view.findViewById<TextView>(R.id.contestDescription)
        private val typeText = view.findViewById<TextView>(R.id.contestType)
        private val parent = view.findViewById<CardView>(R.id.parentContest)
        fun bind(contest: Contest) {
            titleText.text = contest.name
            descriptionText.text = contest.description
            typeText.text = contest.type

            parent.setOnClickListener {
                val tr = manager.beginTransaction()
                val frag = ProblemSetFragment()
                val bundle = Bundle()
                bundle.putString("contestCode", contest.code)
                frag.arguments = bundle
                tr.replace(R.id.nav_host_fragment, frag)
                tr.addToBackStack("Contest")
                tr.commit()
            }
        }
    }
}