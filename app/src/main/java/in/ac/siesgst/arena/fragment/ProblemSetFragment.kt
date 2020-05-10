package `in`.ac.siesgst.arena.fragment


import `in`.ac.siesgst.arena.R
import `in`.ac.siesgst.arena.adapter.ProblemsAdapter
import `in`.ac.siesgst.arena.model.Problem
import `in`.ac.siesgst.arena.util.RetrofitClient
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class ProblemSetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_problem_set, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.problemsRecyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressProblems)
        val list = mutableListOf<Problem>()
        val problemsAdapter = ProblemsAdapter(list)
        recyclerView.adapter = problemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        RetrofitClient.instance.problems()
            .enqueue(object: Callback<List<Problem>>{
                override fun onFailure(call: Call<List<Problem>>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(activity, "Unable to load problems!!", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<Problem>>,
                    response: Response<List<Problem>>
                ) {
                    progressBar.visibility = View.GONE
                    if(response.isSuccessful) {
                        var problems = response.body()!!
                        if (arguments != null) {
                            problems = problems.filter { problem -> problem.contestCode == arguments?.getString("contestCode") }
                        }
                        list.addAll(problems)
                        problemsAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(activity, "Unable to load problems!!", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        return view
    }


}
