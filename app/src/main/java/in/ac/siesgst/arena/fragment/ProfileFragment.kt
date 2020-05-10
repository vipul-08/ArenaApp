package `in`.ac.siesgst.arena.fragment


import `in`.ac.siesgst.arena.R
import `in`.ac.siesgst.arena.model.User
import `in`.ac.siesgst.arena.util.RetrofitClient
import `in`.ac.siesgst.arena.util.Utils
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.Intent.*
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    lateinit var user: User
    lateinit var username: String

    private lateinit var profileProgress: ProgressBar
    private lateinit var mainView: RelativeLayout
    private lateinit var designationView: TextView
    private lateinit var nameView: TextView
    private lateinit var usernameView: TextView
    private lateinit var ratingsView: TextView
    private lateinit var bioContentView: TextView
    private lateinit var codechefView: LinearLayout
    private lateinit var codeforcesView: LinearLayout
    private lateinit var githubView: LinearLayout
    private lateinit var sendMailView: LinearLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        profileProgress = rootView.findViewById(R.id.profile_progress)
        mainView = rootView.findViewById(R.id.main_view)
        designationView = rootView.findViewById(R.id.designation)
        nameView = rootView.findViewById(R.id.name)
        usernameView = rootView.findViewById(R.id.username)
        ratingsView = rootView.findViewById(R.id.ratings)
        bioContentView = rootView.findViewById(R.id.bioContent)
        codechefView = rootView.findViewById(R.id.codechefView)
        codeforcesView = rootView.findViewById(R.id.codeforcesView)
        githubView = rootView.findViewById(R.id.githubView)
        sendMailView = rootView.findViewById(R.id.sendMailView)

        if (arguments == null) {
            val sharedPreferences = activity?.getSharedPreferences("userInfo", MODE_PRIVATE)
            username = sharedPreferences?.getString("username", "null")!!
        }
        else {
            username = arguments?.getString("username").toString()
        }
        RetrofitClient.instance.user(username)
            .enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(activity, "Error finding User!!", Toast.LENGTH_SHORT).show()
                    profileProgress.visibility = GONE
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    profileProgress.visibility = GONE
                    if (response.isSuccessful) {
                        user = response.body()!!
                        mainView.visibility = VISIBLE
                        designationView.text = Utils.getTitle(user.ratings)
                        designationView.setTextColor(ContextCompat.getColor(activity!!, Utils.getColorCode(user.ratings)))
                        nameView.text = user.name
                        nameView.setTextColor(ContextCompat.getColor(activity!!, Utils.getColorCode(user.ratings)))
                        usernameView.text = "@"+user.username
                        ratingsView.text = user.ratings.toString()
                        if ( user.about == null ) {
                            bioContentView.text = "**no about**"
                        } else if (user.about.isEmpty()) {
                            bioContentView.text = "**no about**"
                        } else {
                            bioContentView.text = user.about
                        }

                        if (user.codechefLink == null) {
                            codechefView.visibility = GONE
                        } else {
                            codechefView.setOnClickListener {
                                val uri = Uri.parse("https://codechef.com/users/"+user.codechefLink)
                                val intent = Intent(ACTION_VIEW, uri)
                                startActivity(intent)
                            }
                        }

                        if (user.codeforcesLink == null) {
                            codeforcesView.visibility = GONE
                        } else {
                            codeforcesView.setOnClickListener {
                                val uri = Uri.parse("https://codeforces.com/profile/"+user.codeforcesLink)
                                val intent = Intent(ACTION_VIEW, uri)
                                startActivity(intent)
                            }
                        }

                        if (user.githubLink == null) {
                            githubView.visibility = GONE
                        } else {
                            githubView.setOnClickListener {
                                val uri = Uri.parse("https://github.com/"+user.githubLink)
                                val intent = Intent(ACTION_VIEW, uri)
                                startActivity(intent)
                            }
                        }

                        sendMailView.setOnClickListener {
                            val intent = Intent(ACTION_SENDTO, Uri.fromParts("mailto",user.email, null))
                            startActivity(createChooser(intent, "Choose Email App"))
                        }

                    }
                    else {
                        Toast.makeText(activity, "Wrong Username!!", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        return rootView
    }


}
