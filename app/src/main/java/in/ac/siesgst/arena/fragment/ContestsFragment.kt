package `in`.ac.siesgst.arena.fragment


import `in`.ac.siesgst.arena.R
import `in`.ac.siesgst.arena.adapter.ContestAdapter
import `in`.ac.siesgst.arena.model.Contest
import `in`.ac.siesgst.arena.util.RetrofitClient
import `in`.ac.siesgst.arena.util.Utils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ContestsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contests, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.contestRecyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressContests)
        val list = mutableListOf<Contest>()
        val contestAdapter = ContestAdapter(list, fragmentManager!!)
        recyclerView.adapter = contestAdapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
//        val btn = view.findViewById<Button>(R.id.lasun)
//        btn.setOnClickListener {
//            val tr = fragmentManager?.beginTransaction()
//            val frag = ProfileFragment()
//            val bundle = Bundle()
//            bundle.putString("username", "ShreyaMukherjee")
//            frag.arguments = bundle
//            tr?.replace(R.id.nav_host_fragment, frag)
//            tr?.addToBackStack("Contest")
//            tr?.commit()
//        }
        RetrofitClient.instance.contests()
            .enqueue(object: Callback<List<Contest>> {
                override fun onFailure(call: Call<List<Contest>>, t: Throwable) {
                    progressBar.visibility = GONE
                    Toast.makeText(activity, "Unable to load contests!!", LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<Contest>>,
                    response: Response<List<Contest>>
                ) {
                    progressBar.visibility = GONE
                    if (response.isSuccessful) {
                        val contests = response.body()!!
                        list.addAll(contests)
                        contestAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(activity, "Unable to load contests!!", LENGTH_SHORT).show()
                    }
                }

            })
        return view
    }


}
